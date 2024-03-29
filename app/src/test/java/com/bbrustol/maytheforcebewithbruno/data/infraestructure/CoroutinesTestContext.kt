package com.bbrustol.maytheforcebewithbruno.data.infraestructure

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
val UI = CoroutineScope(Dispatchers.Unconfined)