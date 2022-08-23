package com.kkirrix.kkirikkiri.presentation.register

import com.arkivanov.decompose.value.Value
import com.kkirrix.kkirikkiri.presentation.root.KRXRoot.Companion.EMAIL_PATTERN

interface KRXRegister {

    val model: Value<Model>

    data class Model(
        val email: String,
        val password: String,
        val birthDate: String,
        val nickname: String
    ) {
        val isValidEmail
            get() = EMAIL_PATTERN.matches(email)

        val isAvailableConfirm
            get() = isValidEmail && email.isNotBlank() && password.isNotBlank() && nickname.isNotBlank()
    }

    sealed class Output {
        object RegisterSuccess : Output()
        object BackPressed : Output()
    }

    fun onConfirm()
    fun onEmailChanged(newEmail: String)
    fun onPasswdChanged(newPasswd: String)
    fun onNicknameChanged(newNickname: String)
    fun onBackPress()
}