package com.kkirrix.kkirikkiri.presentation.main.category.list

import com.arkivanov.decompose.value.Value

interface CategoryList {

    val model: Value<Model>

    data class Model(
        val categories: List<String>
    ) {
        companion object {
            val Empty = Model(emptyList())
        }
    }

    fun onItemClick()
}