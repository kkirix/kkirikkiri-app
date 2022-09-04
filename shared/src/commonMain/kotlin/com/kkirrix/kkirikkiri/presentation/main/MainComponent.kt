package com.kkirrix.kkirikkiri.presentation.main

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.childContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.kkirrix.kkirikkiri.presentation.main.category.CategoryComponent
import com.kkirrix.kkirikkiri.presentation.main.gathering.GatheringComponent

class MainComponent(
    componentContext: ComponentContext
) : KRXMain, ComponentContext by componentContext {

    private val categoryComponent = CategoryComponent(childContext("Category"))
    private val gatheringComponent = GatheringComponent(childContext("Gathering"))

    override val categoryChildStack: Value<ChildStack<*, KRXMain.CategoryChild>>
        = categoryComponent.childStack

    override val gatheringChildStack: Value<ChildStack<*, KRXMain.GatheringChild>>
        = gatheringComponent.childStack
}