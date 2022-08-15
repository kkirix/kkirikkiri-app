package com.kkirrix.kkirikkiri.presentation.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.kkirrix.kkirikkiri.presentation.login.LoginComponent
import com.kkirrix.kkirikkiri.presentation.main.MainComponent

class RootComponent(
    componentContext: ComponentContext
) : KRXRoot, ComponentContext by componentContext {

    private val navigation = StackNavigation<Configuration>()
    private val stack = childStack(
        source = navigation,
        initialConfiguration = Configuration.Login,
        handleBackButton = true,
        childFactory = ::resolveChild
    )
    override val childStack: Value<ChildStack<*, KRXRoot.Child>> = stack

    private fun resolveChild(
        configuration: Configuration,
        componentContext: ComponentContext
    ) = when (configuration) {
        is Configuration.Login -> KRXRoot.Child.Login(LoginComponent(componentContext, ::onLoginSuccess))
        is Configuration.Main -> KRXRoot.Child.Main(MainComponent(componentContext))
    }

    private fun onLoginSuccess() = navigation.push(Configuration.Main)

    private sealed interface Configuration : Parcelable {
        @Parcelize
        object Login : Configuration

        @Parcelize
        object Main : Configuration
    }
}