package com.kkirrix.kkirikkiri.di

import org.koin.dsl.bind
import org.koin.dsl.module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.factoryOf
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.kkirrix.kkirikkiri.presentation.login.store.LoginStore
import com.kkirrix.kkirikkiri.presentation.login.store.LoginStoreImpl
import com.kkirrix.kkirikkiri.presentation.register.store.RegisterStore
import com.kkirrix.kkirikkiri.presentation.register.store.RegisterStoreImpl
import com.kkirrix.kkirikkiri.domain.repository.UserRepository
import com.kkirrix.kkirikkiri.domain.interactor.LoginUseCase
import com.kkirrix.kkirikkiri.data.repository.UserRepositoryImpl

val repositoryModule = module {
    singleOf(::UserRepositoryImpl) bind UserRepository::class
}

val interactorModule = module {
    singleOf(::LoginUseCase)
}

val storeModule = module {
    singleOf(::DefaultStoreFactory) bind StoreFactory::class

    factoryOf(::LoginStoreImpl) bind LoginStore::class
    factoryOf(::RegisterStoreImpl) bind RegisterStore::class
}

val dataModule = repositoryModule + interactorModule + storeModule