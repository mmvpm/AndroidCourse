package com.nikitastroganov.androidcourse.ui.userlist

import androidx.lifecycle.viewModelScope
import com.haroldadmin.cnradapter.NetworkResponse
import com.nikitastroganov.androidcourse.BuildConfig
import com.nikitastroganov.androidcourse.data.network.Api
import com.nikitastroganov.androidcourse.data.network.MockApi
import com.nikitastroganov.androidcourse.entity.User
import com.nikitastroganov.androidcourse.ui.base.BaseViewModel
import com.squareup.moshi.Moshi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class UserListViewModel : BaseViewModel() {

    sealed class ViewState {
        object Loading : ViewState()
        data class Data(val userList: List<User>) : ViewState()
    }

    private val _viewState = MutableStateFlow<ViewState>(ViewState.Loading)

    val viewState: Flow<ViewState> get() = _viewState.asStateFlow()

    init {
        viewModelScope.launch {
            _viewState.emit(ViewState.Loading)
            val users = loadUsers()
            _viewState.emit(ViewState.Data(users))
        }
    }

    private suspend fun loadUsers(): List<User> {
        return when (val response = provideApi().getUsers()) {
            is NetworkResponse.Success -> {
                response.body
            }
            else -> {
                // no users
                listOf()
            }
        }
    }

    private fun provideApi(): Api =
        if (BuildConfig.USE_MOCK_BACKEND_API) {
            MockApi()
        } else {
            Retrofit.Builder()
                .client(provideOkHttpClient())
                .baseUrl("https://reqres.in/api/")
                .addConverterFactory(MoshiConverterFactory.create(provideMoshi()))
                .build()
                .create(Api::class.java)
        }

    private fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder().build()

    private fun provideMoshi(): Moshi =
        Moshi.Builder().build()
}