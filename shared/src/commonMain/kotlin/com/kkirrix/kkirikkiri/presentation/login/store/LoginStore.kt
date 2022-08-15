package com.kkirrix.kkirikkiri.presentation.login.store

import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineExecutorFactory
import com.kkirrix.kkirikkiri.domain.interactor.ThirdPartyLoginUseCase
import com.kkirrix.kkirikkiri.presentation.login.Platform
import com.kkirrix.kkirikkiri.presentation.login.store.LoginStore.Intent
import com.kkirrix.kkirikkiri.presentation.login.store.LoginStore.State
import com.kkirrix.kkirikkiri.presentation.login.store.LoginStore.Label

interface LoginStore : Store<Intent, State, Label> {

    data class State(
        val result: Result<Any> = Result.success(Any())
    )

    sealed class Intent {
        data class Login(val platform: Platform) : Intent()
    }

    sealed class Label {
        object Success : Label()
        object Failure : Label()
    }
}

class LoginStoreImpl(
    private val storeFactory: StoreFactory,
    private val thirdPartyLogin: ThirdPartyLoginUseCase
) : LoginStore, Store<Intent, State, Label> by storeFactory.create(
    initialState = State(),
    executorFactory = coroutineExecutorFactory {
        onIntent<Intent.Login> {
            thirdPartyLogin(it.platform)
        }
    }
)