package com.kkirrix.kkirikkiri.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kkirrix.kkirikkiri.presentation.login.KRXLogin

@Composable
fun LoginContent(component: KRXLogin) {
    Column(
        modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center),
        verticalArrangement = Arrangement.spacedBy(47.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(component::onNaverLogin) {
            Text("Naver")
        }

        Button(component::onKakaoLogin) {
            Text("Kakao")
        }
    }
}