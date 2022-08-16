package com.kkirrix.kkirikkiri.presentation.login

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.binder.BinderLifecycleMode
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
        bind(lifecycle, BinderLifecycleMode.CREATE_DESTROY) {
            store.labels bindTo(::bindLabel)
        }
    }

    override fun onNaverLogin() = store.accept(LoginStore.Intent.Login(Platform.NAVER))
    override fun onKakaoLogin() = store.accept(LoginStore.Intent.Login(Platform.KAKAO))

    private fun bindLabel(label: LoginStore.Label) = when (label) {
        is LoginStore.Label.Success -> onLoginSuccess()
        is LoginStore.Label.Failure -> Unit
    }
}