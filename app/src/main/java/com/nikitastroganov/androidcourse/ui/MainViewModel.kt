package com.nikitastroganov.androidcourse.ui

import com.nikitastroganov.androidcourse.interactor.AuthInteractor
import com.nikitastroganov.androidcourse.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val authInteractor: AuthInteractor
) : BaseViewModel() {

    suspend fun isAuthorizedFlow(): Flow<Boolean> =
        authInteractor.isAuthorized()
}