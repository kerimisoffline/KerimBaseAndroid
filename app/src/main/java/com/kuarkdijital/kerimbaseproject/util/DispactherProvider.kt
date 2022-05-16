package com.kuarkdijital.kerimbaseproject.util

import kotlinx.coroutines.CoroutineDispatcher

interface DispactherProvider {
    val main: CoroutineDispatcher
    val io: CoroutineDispatcher
    val default: CoroutineDispatcher
    val unconfined: CoroutineDispatcher
}