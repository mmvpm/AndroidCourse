package com.nikitastroganov.androidcourse.ui.userlist

import androidx.lifecycle.viewModelScope
import com.haroldadmin.cnradapter.NetworkResponse
import com.nikitastroganov.androidcourse.data.network.Api
import com.nikitastroganov.androidcourse.entity.User
import com.nikitastroganov.androidcourse.interactor.UsersInteractor
import com.nikitastroganov.androidcourse.ui.base.BaseViewModel
import com.squareup.moshi.Moshi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject

class UserListViewModel @Inject constructor(
    private val usersInteractor: UsersInteractor
) : BaseViewModel() {

    sealed class ViewState {
        object Loading : ViewState()
        data class Data(val userList: List<User>) : ViewState()
    }

    private val _viewState = MutableStateFlow<ViewState>(ViewState.Loading)

    val viewState: Flow<ViewState> get() = _viewState.asStateFlow()

    init {
        loadUsers()
    }

    private fun loadUsers() {
        viewModelScope.launch {
            _viewState.emit(ViewState.Loading)
            when (val response = usersInteractor.loadUsers()) {
                is NetworkResponse.Success ->
                    _viewState.emit(ViewState.Data(response.body))
                else -> {
                    // something...
                }
            }
        }
    }
}