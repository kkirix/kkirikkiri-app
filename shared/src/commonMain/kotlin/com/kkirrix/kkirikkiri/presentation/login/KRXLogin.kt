package com.kkirrix.kkirikkiri.presentation.login

import com.arkivanov.decompose.value.Value
import com.kkirrix.kkirikkiri.presentation.root.KRXRoot.Companion.EMAIL_PATTERN

interface KRXLogin {

    val model: Value<Model>

    data class Model(
        val email: String,
        val password: String
    ) {
        val isValidEmail: Boolean
            get() = EMAIL_PATTERN matches email

        val isAvailableLogin: Boolean
            get() = isValidEmail && email.isNotBlank() && password.isNotBlank()
    }

    sealed class Output {
        object LoginSuccess : Output()
        object NavigateToRegister : Output()
    }

    fun onLogin()
    fun onEmailChanged(newEmail: String)
    fun onPasswdChanged(newPasswd: String)
    fun onCreateAccount()
}