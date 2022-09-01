package com.kkirrix.kkirikkiri.presentation.main

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize

class MainComponent(
    componentContext: ComponentContext
) : KRXMain, ComponentContext by componentContext {

    private val navigation = StackNavigation<Configuration>()

    private val _screen: MutableValue<KRXMain.Tab> = MutableValue(KRXMain.Tab.FIRST)
    override val screen: Value<KRXMain.Tab> get() = _screen

    override fun onBottomTabClick(tab: KRXMain.Tab) {
        _screen.value = tab
    }

    private sealed class Configuration : Parcelable {
        @Parcelize
        object FirstTab: Configuration()

        @Parcelize
        object SecondTab: Configuration()

        @Parcelize
        object ThirdTab: Configuration()
    }
}