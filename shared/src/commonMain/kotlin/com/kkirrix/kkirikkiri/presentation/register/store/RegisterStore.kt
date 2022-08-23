package com.kkirrix.kkirikkiri.presentation.register.store

import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.core.utils.ExperimentalMviKotlinApi
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineExecutorFactory
import com.kkirrix.kkirikkiri.presentation.register.KRXRegister.Model
import com.kkirrix.kkirikkiri.presentation.register.store.RegisterStore.Intent
import com.kkirrix.kkirikkiri.presentation.register.store.RegisterStore.Label

interface RegisterStore : Store<Intent, Model, Label> {

    sealed class Intent {
        data class EmailInput(val newEmail: String) : Intent()
        data class PasswdInput(val newPasswd: String) : Intent()
        data class NicknameInput(val newNickname: String) : Intent()
        object Confirm : Intent()
    }

    sealed class Label {
        object Success : Label()
        object Failure : Label()
    }
}

@OptIn(ExperimentalMviKotlinApi::class)
class RegisterStoreImpl(
    storeFactory: StoreFactory
) : RegisterStore, Store<Intent, Model, Label> by storeFactory.create(
    initialState = Model("", "", "", ""),
    executorFactory = coroutineExecutorFactory {
        onIntent<Intent.EmailInput> {
            dispatch(Message.EmailChanged(it.newEmail))
        }
        onIntent<Intent.PasswdInput> {
            dispatch(Message.PasswdChanged(it.newPasswd))
        }
        onIntent<Intent.NicknameInput> {
            dispatch(Message.NicknameChanged(it.newNickname))
        }
        onIntent<Intent.Confirm> {
            publish(Label.Success)
        }
    },
    reducer = { msg: Message ->
        when (msg) {
            is Message.EmailChanged -> copy(email = msg.newEmail)
            is Message.PasswdChanged -> copy(password = msg.newPasswd)
            is Message.NicknameChanged -> copy(nickname = msg.newNickname)
        }
    }
) {
    private sealed class Message {
        data class EmailChanged(val newEmail: String) : Message()
        data class PasswdChanged(val newPasswd: String) : Message()
        data class NicknameChanged(val newNickname: String) : Message()
    }
}