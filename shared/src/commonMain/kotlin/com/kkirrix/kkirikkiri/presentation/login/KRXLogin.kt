package com.kkirrix.kkirikkiri.presentation.login

import com.arkivanov.decompose.value.Value

interface KRXLogin {

    val model: Value<Model>

    data class Model(
        val email: String,
        val password: String
    ) {
        val isAvailableLogin: Boolean
            get() = email.isNotBlank() && password.isNotBlank()
    }

    fun onLogin()
    fun onEmailChanged(newEmail: String)
    fun onPasswdChanged(newPasswd: String)
}