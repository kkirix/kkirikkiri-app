package com.kkirrix.kkirikkiri.presentation.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.*
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.kkirrix.kkirikkiri.presentation.login.KRXLogin
import com.kkirrix.kkirikkiri.presentation.login.LoginComponent
import com.kkirrix.kkirikkiri.presentation.main.MainComponent
import com.kkirrix.kkirikkiri.presentation.register.KRXRegister
import com.kkirrix.kkirikkiri.presentation.register.RegisterComponent

class RootComponent(
    componentContext: ComponentContext
) : KRXRoot, ComponentContext by componentContext {

    private val navigation = StackNavigation<Configuration>()
    override val childStack: Value<ChildStack<*, KRXRoot.Child>> = childStack(
        source = navigation,
        initialConfiguration = Configuration.Login,
        handleBackButton = true,
        childFactory = ::resolveChild
    )

    private fun resolveChild(
        configuration: Configuration,
        componentContext: ComponentContext
    ) = when (configuration) {
        is Configuration.Login -> KRXRoot.Child.Login(LoginComponent(componentContext, ::onLoginOutput))
        is Configuration.Register -> KRXRoot.Child.Register(RegisterComponent(componentContext, ::onRegisterOutput))
        is Configuration.Main -> KRXRoot.Child.Main(MainComponent(componentContext))
    }

    private fun onRegisterOutput(output: KRXRegister.Output) = when (output) {
        is KRXRegister.Output.RegisterSuccess -> navigation.replaceCurrent(Configuration.Main)
        is KRXRegister.Output.BackPressed -> navigation.pop()
    }

    private fun onLoginOutput(output: KRXLogin.Output) = when (output) {
        is KRXLogin.Output.LoginSuccess -> navigation.replaceCurrent(Configuration.Main)
        is KRXLogin.Output.NavigateToRegister -> navigation.push(Configuration.Register)
    }

    private sealed interface Configuration : Parcelable {
        @Parcelize
        object Login : Configuration

        @Parcelize
        object Main : Configuration

        @Parcelize
        object Register : Configuration
    }
}