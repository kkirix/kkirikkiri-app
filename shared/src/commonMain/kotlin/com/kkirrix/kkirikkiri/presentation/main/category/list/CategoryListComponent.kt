package com.kkirrix.kkirikkiri.presentation.main.category.list

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.instancekeeper.getOrCreate
import com.arkivanov.essenty.statekeeper.consume
import com.kkirrix.kkirikkiri.domain.interactor.GetCategoriesUseCase
import com.kkirrix.kkirikkiri.util.CoroutineInstance
import kotlinx.coroutines.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class CategoryListComponent(
    componentContext: ComponentContext,
    private val onItemClick: () -> Unit
) : CategoryList, KoinComponent, ComponentContext by componentContext {

    private val handler = instanceKeeper.getOrCreate {
        Handler(stateKeeper.consume("STATE")?: CategoryList.Model.Empty, get())
    }
    override val model: Value<CategoryList.Model> = handler.model

    override fun onItemClick() {
        onItemClick.invoke()
    }

    private class Handler(
        initialState: CategoryList.Model,
        getCategoriesUseCase: GetCategoriesUseCase
    ) : CoroutineInstance() {

        private val _model = MutableValue(initialState)
        val model: Value<CategoryList.Model> get() = _model

        init {
            instanceScope.launch {
                val categories = getCategoriesUseCase()
                _model.value = _model.value.copy(categories = categories)
            }
        }
    }
}