package com.kkirrix.kkirikkiri.category

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.kkirrix.kkirikkiri.bottomnav.Direction
import com.kkirrix.kkirikkiri.presentation.main.KRXMain

fun NavGraphBuilder.categoryGraph(stack: Value<ChildStack<*, KRXMain.CategoryChild>>) {
    composable(Direction.Category.route) {
        Children(stack) {
            when (val child = it.instance) {
                is KRXMain.CategoryChild.List -> CategoryListContent(component = child.component)
                is KRXMain.CategoryChild.Landing -> CategoryLandingContent(component = child.component)
            }
        }
    }
}