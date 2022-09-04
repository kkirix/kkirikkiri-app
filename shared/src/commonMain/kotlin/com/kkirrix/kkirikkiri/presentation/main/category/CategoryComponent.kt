package com.kkirrix.kkirikkiri.presentation.main.category

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.kkirrix.kkirikkiri.presentation.main.KRXMain
import com.kkirrix.kkirikkiri.presentation.main.category.landing.CategoryLandingComponent
import com.kkirrix.kkirikkiri.presentation.main.category.list.CategoryListComponent

internal class CategoryComponent(
    componentContext: ComponentContext
) : ComponentContext by componentContext {

    private val navigation = StackNavigation<Configuration>()
    val childStack: Value<ChildStack<*, KRXMain.CategoryChild>> = childStack(
        source = navigation,
        initialConfiguration = Configuration.List,
        handleBackButton = true,
        childFactory = ::resolveChild
    )

    private fun resolveChild(
        configuration: Configuration,
        componentContext: ComponentContext
    ) = when (configuration) {
        is Configuration.List -> KRXMain.CategoryChild.List(CategoryListComponent(componentContext, ::onCategoryListItemClick))
        is Configuration.Landing -> KRXMain.CategoryChild.Landing(CategoryLandingComponent(componentContext))
    }

    private fun onCategoryListItemClick() {

    }

    private sealed class Configuration : Parcelable {
        @Parcelize
        object List : Configuration()

        @Parcelize
        object Landing : Configuration()
    }
}