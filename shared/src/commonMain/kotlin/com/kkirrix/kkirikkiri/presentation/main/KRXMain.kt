package com.kkirrix.kkirikkiri.presentation.main

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.kkirrix.kkirikkiri.presentation.main.category.landing.CategoryLanding
import com.kkirrix.kkirikkiri.presentation.main.category.list.CategoryList
import com.kkirrix.kkirikkiri.presentation.main.gathering.detail.GatheringDetail
import com.kkirrix.kkirikkiri.presentation.main.gathering.list.GatheringList

interface KRXMain {

    val categoryChildStack: Value<ChildStack<*, CategoryChild>>
    val gatheringChildStack: Value<ChildStack<*, GatheringChild>>


    sealed interface CategoryChild {
        data class List(val component: CategoryList) : CategoryChild
        data class Landing(val component: CategoryLanding) : CategoryChild
    }

    sealed interface GatheringChild {
        data class List(val component: GatheringList) : GatheringChild
        data class Detail(val component: GatheringDetail) : GatheringChild
    }
}