package com.kkirrix.kkirikkiri.presentation.root

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.kkirrix.kkirikkiri.presentation.login.KRXLogin
import com.kkirrix.kkirikkiri.presentation.main.KRXMain
import com.kkirrix.kkirikkiri.presentation.register.KRXRegister

interface KRXRoot {

    val childStack: Value<ChildStack<*, Child>>

    sealed interface Child {
        data class Login(val component: KRXLogin) : Child
        data class Register(val component: KRXRegister) : Child
        data class Main(val component: KRXMain) : Child
    }

    companion object {
        val EMAIL_PATTERN = Regex(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}\\@[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}(\\.[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25})+"
        )
    }
}