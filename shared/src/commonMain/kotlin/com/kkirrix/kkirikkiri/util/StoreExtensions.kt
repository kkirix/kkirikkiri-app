package com.kkirrix.kkirikkiri.util

import com.arkivanov.decompose.value.Value
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.extensions.coroutines.states
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

fun <T: Any> Store<*, T, *>.asValue() = object : Value<T>() {
    private var job: Job? = null
    override val value: T = state

    override fun subscribe(observer: (T) -> Unit) {
        job?.cancel()
        job = CoroutineScope(Dispatchers.Main.immediate + Job()).launch {
            states.collect(observer)
        }
    }

    override fun unsubscribe(observer: (T) -> Unit) {
        job?.cancel()
    }
}