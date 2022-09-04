package com.kkirrix.kkirikkiri.login

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState
import com.kkirrix.kkirikkiri.R
import com.kkirrix.kkirikkiri.component.TitleTextField
import com.kkirrix.kkirikkiri.presentation.login.KRXLogin

@Composable
fun LoginContent(component: KRXLogin) {
    val model by component.model.subscribeAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier
            .fillMaxSize(0.88f)
            .align(Alignment.Center)
        ) {
            LoginLogo()
            Spacer(modifier = Modifier.height(56.dp))
            TitleTextField(
                title = "Email Address",
                value = model.email,
                onValueChange = component::onEmailChanged,
                trailingIcon = { if (model.email.isNotBlank()) ValidIcon(isValid = model.isValidEmail) }
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
                onClick = component::onLogin,
                enabled = model.isAvailableLogin,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Login")
            }
            Spacer(modifier = Modifier.height(36.dp))
            Text(
                text = "Create Account",
                fontSize = 18.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally).clickable(enabled = true, onClick = component::onCreateAccount)
            )
        }
    }
}

@Composable
private fun LoginLogo() {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(
            text = "Login",
            fontSize = 24.sp
        )
        Text(
            text = "Login to get started",
            fontSize = 14.sp
        )
    }
}

@Composable
private fun ValidIcon(isValid: Boolean) {
    Crossfade(targetState = isValid) {
        if (!it) Image(painter = painterResource(id = R.drawable.ic_invalid), contentDescription = null)
    }
}