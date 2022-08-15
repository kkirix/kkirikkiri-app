package com.kkirrix.kkirikkiri.di

import org.koin.dsl.bind
import org.koin.dsl.module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.factoryOf
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.kkirrix.kkirikkiri.presentation.login.store.LoginStore
import com.kkirrix.kkirikkiri.presentation.login.store.LoginStoreImpl
import com.kkirrix.kkirikkiri.domain.repository.ThirdPartyRepository
import com.kkirrix.kkirikkiri.domain.interactor.ThirdPartyLoginUseCase
import com.kkirrix.kkirikkiri.data.repository.ThirdPartyRepositoryImpl

val repositoryModule = module {
    singleOf(::ThirdPartyRepositoryImpl) bind ThirdPartyRepository::class
}

val interactorModule = module {
    singleOf(::ThirdPartyLoginUseCase)
}

val storeModule = module {
    singleOf(::DefaultStoreFactory) bind StoreFactory::class
    factoryOf(::LoginStoreImpl) bind LoginStore::class
}

val dataModule = repositoryModule + interactorModule + storeModule