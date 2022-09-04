package com.kkirrix.kkirikkiri.presentation.main.gathering.list

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.instancekeeper.getOrCreate
import com.arkivanov.essenty.statekeeper.consume
import com.kkirrix.kkirikkiri.domain.interactor.GetGatheringsUseCase
import com.kkirrix.kkirikkiri.util.CoroutineInstance
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class GatheringListComponent(
    componentContext: ComponentContext,
    private val onItemClick: () -> Unit
) : GatheringList, KoinComponent, ComponentContext by componentContext {

    private val handler = instanceKeeper.getOrCreate {
        Handler(stateKeeper.consume("STATE")?: GatheringList.Model.Empty, get())
    }
    override val model: Value<GatheringList.Model> = handler.model

    override fun onItemClick() {

    }

    private class Handler(
        initialState: GatheringList.Model,
        getGatheringUseCase: GetGatheringsUseCase
    ) : CoroutineInstance() {

        private val _model = MutableValue(initialState)
        val model: Value<GatheringList.Model> get() = _model

        init {
            instanceScope.launch {
                val gatherings = getGatheringUseCase()
                _model.value = _model.value.copy(gatherings = gatherings)
            }
        }
    }
}