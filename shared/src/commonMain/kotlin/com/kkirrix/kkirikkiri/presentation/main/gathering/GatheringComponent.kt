package com.kkirrix.kkirikkiri.presentation.main.gathering

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.kkirrix.kkirikkiri.presentation.main.KRXMain.GatheringChild
import com.kkirrix.kkirikkiri.presentation.main.gathering.detail.GatheringDetailComponent
import com.kkirrix.kkirikkiri.presentation.main.gathering.list.GatheringListComponent

internal class GatheringComponent(
    componentContext: ComponentContext
) : ComponentContext by componentContext {

    private val navigation = StackNavigation<Configuration>()
    val childStack: Value<ChildStack<*, GatheringChild>> = childStack(
        source = navigation,
        initialConfiguration = Configuration.List,
        handleBackButton = true,
        childFactory = ::resolveChild
    )

    private fun resolveChild(
        configuration: Configuration,
        componentContext: ComponentContext
    ) = when (configuration) {
        is Configuration.List -> GatheringChild.List(GatheringListComponent(componentContext, {}))
        is Configuration.Detail -> GatheringChild.Detail(GatheringDetailComponent(componentContext))
    }

    private sealed class Configuration : Parcelable {
        @Parcelize
        object List : Configuration()

        @Parcelize
        object Detail : Configuration()
    }
}