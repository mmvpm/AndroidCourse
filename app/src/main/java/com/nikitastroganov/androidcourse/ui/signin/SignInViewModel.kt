package com.nikitastroganov.androidcourse.ui.signin

import androidx.lifecycle.viewModelScope
import com.haroldadmin.cnradapter.NetworkResponse
import com.nikitastroganov.androidcourse.data.network.response.error.SignInWithEmailErrorResponse
import com.nikitastroganov.androidcourse.interactor.AuthInteractor
import com.nikitastroganov.androidcourse.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val authInteractor: AuthInteractor
) : BaseViewModel() {

    private val _signInActionStateFlow =
        MutableStateFlow<SignInActionState>(SignInActionState.Pending)

    fun signInActionStateFlow(): Flow<SignInActionState> {
        return _signInActionStateFlow.asStateFlow()
    }

    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            _signInActionStateFlow.emit(SignInActionState.Loading)
            try {
                when (val response = authInteractor.signInWithEmail(email, password)) {
                    is NetworkResponse.Success -> {
                        _signInActionStateFlow.emit(SignInActionState.Pending)
                    }
                    is NetworkResponse.ServerError -> {
                        _signInActionStateFlow.emit(SignInActionState.ServerError(response))
                    }
                    is NetworkResponse.NetworkError -> {
                        _signInActionStateFlow.emit(SignInActionState.NetworkError(response))
                    }
                    is NetworkResponse.UnknownError -> {
                        _signInActionStateFlow.emit(SignInActionState.UnknownError(response))
                    }
                }
            } catch (error: Throwable) {
                Timber.e(error)
                _signInActionStateFlow.emit(
                    SignInActionState.UnknownError(
                        NetworkResponse.UnknownError(
                            error
                        )
                    )
                )
            }
        }
    }

    sealed class SignInActionState {
        object Pending : SignInActionState()
        object Loading : SignInActionState()
        data class ServerError(val e: NetworkResponse.ServerError<SignInWithEmailErrorResponse>) :
            SignInActionState()

        data class NetworkError(val e: NetworkResponse.NetworkError) : SignInActionState()
        data class UnknownError(val e: NetworkResponse.UnknownError) : SignInActionState()
    }
}