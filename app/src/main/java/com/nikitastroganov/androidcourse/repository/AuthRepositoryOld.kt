package com.nikitastroganov.androidcourse.repository

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

object AuthRepositoryOld {

    private val isAuthorizedFlowInternal = MutableStateFlow(false)
    val isAuthorizedFlow = isAuthorizedFlowInternal.asStateFlow()

    suspend fun signIn(email: String, password: String) {
        isAuthorizedFlowInternal.emit(true)
    }

    suspend fun signUp(firstname: String,
                       lastname: String,
                       nickname: String,
                       email: String,
                       password: String) {
        //TODO: Get API response for email availability, change screen to email confirm
    }

    suspend fun logout() {
        isAuthorizedFlowInternal.emit(false)
    }
}