package com.kkirrix.kkirikkiri.presentation.login

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value
import com.arkivanov.mvikotlin.core.binder.BinderLifecycleMode
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.extensions.coroutines.bind
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.kkirrix.kkirikkiri.presentation.login.store.LoginStore
import com.kkirrix.kkirikkiri.util.asValue
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class LoginComponent(
    componentContext: ComponentContext,
    private val onLoginSuccess: () -> Unit
) : KRXLogin, KoinComponent, ComponentContext by componentContext {

    private val store: LoginStore = instanceKeeper.getStore(::get)

    override val model: Value<KRXLogin.Model> = store.asValue()

    init {
        bind(lifecycle, BinderLifecycleMode.CREATE_DESTROY) {
            store.labels bindTo(::bindLabel)
        }
    }

    override fun onEmailChanged(newEmail: String) {
        store.accept(LoginStore.Intent.EmailInput(newEmail))
    }

    override fun onPasswdChanged(newPasswd: String) {
        store.accept(LoginStore.Intent.PasswdInput(newPasswd))
    }

    override fun onLogin() {
        store.accept(LoginStore.Intent.Login)
    }

    private fun bindLabel(label: LoginStore.Label) = when (label) {
        is LoginStore.Label.Success -> onLoginSuccess()
        is LoginStore.Label.Failure -> Unit
    }
}