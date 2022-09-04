package com.kkirrix.kkirikkiri.util

import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel

abstract class CoroutineInstance : InstanceKeeper.Instance {
    protected val instanceScope = CoroutineScope(Dispatchers.Main.immediate + Job())

    override fun onDestroy() {
        instanceScope.cancel()
    }
}