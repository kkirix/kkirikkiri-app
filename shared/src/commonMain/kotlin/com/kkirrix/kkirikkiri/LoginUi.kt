package com.kkirrix.kkirikkiri

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kkirrix.kkirikkiri.naver.AuthCallback
import com.kkirrix.kkirikkiri.naver.Naver

@Composable
fun LoginContent(authCallback: AuthCallback) {
    Column(
        modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center),
        verticalArrangement = Arrangement.spacedBy(28.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                Naver.auth.authenticate(authCallback)
            }
        ) {
            Text("Naver Login")
        }
    }
}