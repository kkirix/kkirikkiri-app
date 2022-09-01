package com.kkirrix.kkirikkiri.presentation.main

import com.arkivanov.decompose.value.Value

interface KRXMain {
    val screen: Value<Tab>

    fun onBottomTabClick(tab: Tab)

    enum class Tab {
        FIRST, SECOND, THIRD, FOURTH
    }
}