package com.kkirrix.kkirikkiri.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.ui.platform.ComposeView
import com.kkirrix.kkirikkiri.LoginContent
import com.kkirrix.kkirikkiri.naver.AuthCallback
import com.kkirrix.kkirikkiri.naver.Naver

class MainActivity : AppCompatActivity() {

    private val authCallback = object : AuthCallback {
        override fun onSuccess() {

        }

        override fun onError(errorCode: Int, message: String) {

        }

        override fun onFailure(httpStatus: Int, message: String) {

        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        Naver.auth.initialize(application, "AbHsG_wTKLoq9k5eOK4A", "WZHJxwDCDf", "끼리끼리")
        findViewById<ComposeView>(R.id.compose_view).setContent {
            LoginContent(authCallback = authCallback)
        }
    }
}
