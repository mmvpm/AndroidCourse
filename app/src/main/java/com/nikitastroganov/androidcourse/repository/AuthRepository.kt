package com.nikitastroganov.androidcourse.repository

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

object AuthRepository {

    private val isAuthorizedFlowInternal = MutableStateFlow(false)
    val isAuthorizedFlow = isAuthorizedFlowInternal.asStateFlow()

    suspend fun signIn() {
        isAuthorizedFlowInternal.emit(true)
    }

    suspend fun logout() {
        isAuthorizedFlowInternal.emit(false)
    }
}