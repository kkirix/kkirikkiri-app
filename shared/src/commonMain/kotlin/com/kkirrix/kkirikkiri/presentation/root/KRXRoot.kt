package com.kkirrix.kkirikkiri.presentation.root

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.kkirrix.kkirikkiri.presentation.login.KRXLogin
import com.kkirrix.kkirikkiri.presentation.main.KRXMain

interface KRXRoot {
    val childStack: Value<ChildStack<*, Child>>

    sealed interface Child {
        data class Login(val component: KRXLogin) : Child
        data class Main(val component: KRXMain) : Child
    }
}