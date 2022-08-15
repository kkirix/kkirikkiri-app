package com.kkirrix.kkirikkiri.presentation.login

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.extensions.coroutines.bind
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.kkirrix.kkirikkiri.presentation.login.store.LoginStore
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class LoginComponent(
    componentContext: ComponentContext,
    private val onLoginSuccess: () -> Unit
) : KRXLogin, KoinComponent, ComponentContext by componentContext {

    private val store: LoginStore = instanceKeeper.getStore(::get)

    init {
        bind {
            store.labels bindTo(::bindLabel)
        }
    }

    override fun onLogin(platform: Platform) = store.accept(LoginStore.Intent.Login(platform))

    private fun bindLabel(label: LoginStore.Label) = when (label) {
        is LoginStore.Label.Success -> onLoginSuccess()
        is LoginStore.Label.Failure -> Unit
    }
}