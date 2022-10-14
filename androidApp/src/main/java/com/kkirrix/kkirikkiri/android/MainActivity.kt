package com.kkirrix.kkirikkiri.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.ui.viewinterop.AndroidView
import com.kakao.sdk.user.UserApiClient
import com.navercorp.nid.oauth.OAuthLoginCallback
import com.navercorp.nid.oauth.view.NidOAuthLoginButton

class MainActivity : AppCompatActivity(), OAuthLoginCallback {

    private val launcher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        println("${it.resultCode}")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Column {
                AndroidView(
                    factory = {
                        NidOAuthLoginButton(it).also {
                            it.setOAuthLogin(launcher, this@MainActivity)
                        }
                    }
                )
                Button(onClick = {
                    UserApiClient.instance.loginWithKakaoTalk(this@MainActivity) { token, error ->
                        println("$error, $token")
                    }
                }) {

                }
            }
        }
    }

    override fun onSuccess() {
        println("On Success")
    }

    override fun onFailure(httpStatus: Int, message: String) {
        println("$httpStatus, $message")
    }

    override fun onError(errorCode: Int, message: String) {
        TODO("Not yet implemented")
    }
}
