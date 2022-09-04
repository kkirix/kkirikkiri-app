package com.kkirrix.kkirikkiri.gathering

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.kkirrix.kkirikkiri.bottomnav.Direction
import com.kkirrix.kkirikkiri.presentation.main.KRXMain

fun NavGraphBuilder.gatheringGraph(stack: Value<ChildStack<*, KRXMain.GatheringChild>>) {
    composable(Direction.Gathering.route) {
        Children(stack) {
            when (val child = it.instance) {
                is KRXMain.GatheringChild.List -> GatheringListContent(child.component)
                is KRXMain.GatheringChild.Detail -> GatheringDetailContent(child.component)
            }
        }
    }
}