package com.kkirrix.kkirikkiri.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState
import com.kkirrix.kkirikkiri.presentation.login.KRXLogin

@Composable
fun LoginContent(component: KRXLogin) {
    val model by component.model.subscribeAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(value = model.email, onValueChange = component::onEmailChanged)
        TextField(value = model.password, onValueChange = component::onPasswdChanged)
        Button(
            onClick = component::onLogin,
            enabled = model.isAvailableLogin
        ) {
            Text(text = "Login")
        }
    }
}