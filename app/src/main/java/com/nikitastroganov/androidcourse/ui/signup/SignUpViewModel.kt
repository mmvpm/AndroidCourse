package com.nikitastroganov.androidcourse.ui.signup

import androidx.lifecycle.viewModelScope
import com.nikitastroganov.androidcourse.interactor.AuthInteractor
import com.nikitastroganov.androidcourse.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authInteractor: AuthInteractor
): BaseViewModel() {

    private val _eventChannel = Channel<Event>(Channel.BUFFERED)

    val eventsFlow: Flow<Event>
        get() = _eventChannel.receiveAsFlow()

    fun signUp(
        firstName: String,
        lastName: String,
        nickname: String,
        email: String,
        password: String
    ) {
        viewModelScope.launch {
//            try {
//                authInteractor.signInWithEmail(
//                    email = email,
//                    password = password
//                )
//                _eventChannel.send(Event.SignUpSuccess)
//            } catch (error: Exception) {
//                _eventChannel.send(Event.SignUpEmailConfirmationRequired)
//            }
            _eventChannel.send(Event.SignUpEmailConfirmationRequired)
        }
    }

    sealed class Event {
        object SignUpSuccess : Event()
        object SignUpEmailConfirmationRequired : Event()
    }
}