package com.kkirrix.kkirikkiri.login

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState
import com.kkirrix.kkirikkiri.component.TitleTextField
import com.kkirrix.kkirikkiri.presentation.register.KRXRegister

@Composable
fun RegisterContent(component: KRXRegister) {
    val model by component.model.subscribeAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier
            .fillMaxSize(0.88f)
            .align(Alignment.Center)
        ) {
            RegisterLogo()
            Spacer(modifier = Modifier.height(56.dp))
            TitleTextField(
                title = "Email Address",
                value = model.email,
                onValueChange = component::onEmailChanged
            )
            Spacer(modifier = Modifier.height(36.dp))
            TitleTextField(
                title = "Password",
                value = model.password,
                onValueChange = component::onPasswdChanged,
                visualTransformation = PasswordVisualTransformation()
            )
            Spacer(modifier = Modifier.height(72.dp))
            Button(
                onClick = component::onConfirm,
                enabled = model.isAvailableConfirm,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Create Account")
            }
        }
    }
}

@Composable
private fun RegisterLogo() {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(
            text = "Create Account",
            fontSize = 24.sp
        )
        Text(
            text = "Sign up to get started",
            fontSize = 14.sp
        )
    }
}