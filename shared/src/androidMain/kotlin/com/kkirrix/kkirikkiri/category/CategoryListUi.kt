package com.kkirrix.kkirikkiri.category

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState
import com.kkirrix.kkirikkiri.presentation.main.category.list.CategoryList

@Composable
fun CategoryListContent(component: CategoryList) {
    val model by component.model.subscribeAsState()

    LazyColumn {
        items(model.categories) {
            Card {
                Text(text = it)
            }
        }
    }
}