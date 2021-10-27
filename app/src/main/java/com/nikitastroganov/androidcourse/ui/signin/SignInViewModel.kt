package com.nikitastroganov.androidcourse.ui.signin

import androidx.lifecycle.viewModelScope
import com.nikitastroganov.androidcourse.repository.AuthRepository
import com.nikitastroganov.androidcourse.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class SignInViewModel : BaseViewModel() {

    fun signIn() {
        viewModelScope.launch {
            AuthRepository.signIn()
        }
    }
}