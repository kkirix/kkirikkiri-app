package com.kkirrix.kkirikkiri.presentation.login.store

import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.core.utils.ExperimentalMviKotlinApi
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineExecutorFactory
import com.kkirrix.kkirikkiri.domain.interactor.LoginUseCase
import com.kkirrix.kkirikkiri.presentation.login.KRXLogin.Model
import com.kkirrix.kkirikkiri.presentation.login.store.LoginStore.Intent
import com.kkirrix.kkirikkiri.presentation.login.store.LoginStore.Label
import kotlinx.coroutines.launch

interface LoginStore : Store<Intent, Model, Label> {

    sealed class Intent {
        data class EmailInput(val newEmail: String) : Intent()
        data class PasswdInput(val newPasswd: String) : Intent()
        object Login : Intent()
    }

    sealed class Label {
        object Success : Label()
        object Failure : Label()
    }
}

@OptIn(ExperimentalMviKotlinApi::class)
class LoginStoreImpl(
    private val login: LoginUseCase,
    private val storeFactory: StoreFactory
) : LoginStore, Store<Intent, Model, Label> by storeFactory.create(
    initialState = Model("", ""),
    executorFactory = coroutineExecutorFactory {
        onIntent<Intent.EmailInput> {
            dispatch(Message.EmailChanged(it.newEmail))
        }
        onIntent<Intent.PasswdInput> {
            dispatch(Message.PasswdChanged(it.newPasswd))
        }
        onIntent<Intent.Login> {
            launch {
                val result = login(state.email, state.password)
                val label = if (result.isSuccess) Label.Success else Label.Failure
                publish(label)
            }
        }
    },
    reducer = { msg: Message ->
        when (msg) {
            is Message.EmailChanged -> copy(email = msg.newEmail)
            is Message.PasswdChanged -> copy(password = msg.newPasswd)
        }
    }
) {
    private sealed class Message {
        data class EmailChanged(val newEmail: String) : Message()
        data class PasswdChanged(val newPasswd: String) : Message()
    }
}