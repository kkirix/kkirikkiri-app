package com.kkirrix.kkirikkiri.di

fun startKoin() = org.koin.core.context.startKoin {
    modules(dataModule)
}