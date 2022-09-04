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
import com.kkirrix.kkirikkiri.domain.repository.CategoryRepository
import com.kkirrix.kkirikkiri.domain.repository.GatheringRepository
import com.kkirrix.kkirikkiri.domain.interactor.LoginUseCase
import com.kkirrix.kkirikkiri.domain.interactor.GetCategoriesUseCase
import com.kkirrix.kkirikkiri.domain.interactor.GetGatheringsUseCase
import com.kkirrix.kkirikkiri.data.repository.UserRepositoryImpl
import com.kkirrix.kkirikkiri.data.repository.CategoryRepositoryImpl
import com.kkirrix.kkirikkiri.data.repository.GatheringRepositoryImpl

val repositoryModule = module {
    singleOf(::UserRepositoryImpl) bind UserRepository::class
    singleOf(::CategoryRepositoryImpl) bind CategoryRepository::class
    singleOf(::GatheringRepositoryImpl) bind GatheringRepository::class
}

val interactorModule = module {
    singleOf(::LoginUseCase)
    singleOf(::GetCategoriesUseCase)
    singleOf(::GetGatheringsUseCase)
}

val storeModule = module {
    singleOf(::DefaultStoreFactory) bind StoreFactory::class

    factoryOf(::LoginStoreImpl) bind LoginStore::class
    factoryOf(::RegisterStoreImpl) bind RegisterStore::class
}

val dataModule = repositoryModule + interactorModule + storeModule