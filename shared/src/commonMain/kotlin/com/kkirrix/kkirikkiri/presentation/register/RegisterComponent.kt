package com.kkirrix.kkirikkiri.presentation.register

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value
import com.arkivanov.mvikotlin.core.binder.BinderLifecycleMode
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.extensions.coroutines.bind
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.kkirrix.kkirikkiri.presentation.register.store.RegisterStore
import com.kkirrix.kkirikkiri.util.asValue
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class RegisterComponent(
    componentContext: ComponentContext,
    private val onOutput: (KRXRegister.Output) -> Unit
) : KRXRegister, KoinComponent, ComponentContext by componentContext {

    private val store: RegisterStore = instanceKeeper.getStore(::get)

    override val model: Value<KRXRegister.Model> = store.asValue()

    init {
        bind(lifecycle, BinderLifecycleMode.CREATE_DESTROY) {
            store.labels bindTo ::bindLabel
        }
    }

    override fun onConfirm() {
        store.accept(RegisterStore.Intent.Confirm)
    }

    override fun onEmailChanged(newEmail: String) {
        store.accept(RegisterStore.Intent.EmailInput(newEmail))
    }

    override fun onPasswdChanged(newPasswd: String) {
        store.accept(RegisterStore.Intent.PasswdInput(newPasswd))
    }

    override fun onNicknameChanged(newNickname: String) {
        store.accept(RegisterStore.Intent.NicknameInput(newNickname))
    }

    override fun onBackPress() {
        onOutput(KRXRegister.Output.BackPressed)
    }

    private fun bindLabel(label: RegisterStore.Label) = when (label) {
        is RegisterStore.Label.Success -> onOutput(KRXRegister.Output.RegisterSuccess)
        is RegisterStore.Label.Failure -> Unit
    }

}