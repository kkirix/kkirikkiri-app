package com.kkirrix.kkirikkiri.presentation.main.gathering.list

import com.arkivanov.decompose.value.Value

interface GatheringList {

    val model: Value<Model>

    data class Model(
        val gatherings: List<String>
    ) {
        companion object {
            val Empty = Model(emptyList())
        }
    }

    fun onItemClick()
}