package com.nikitastroganov.androidcourse

import androidx.lifecycle.*
import com.squareup.moshi.Moshi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainViewModel : ViewModel() {

    private val viewStateInternal: MutableStateFlow<ViewState> =
        MutableStateFlow(ViewState.Loading)
    val viewState: StateFlow<ViewState> = viewStateInternal

    init {
        viewModelScope.launch {
            viewStateInternal.value = ViewState.Loading
            val users = loadUsers()
            viewStateInternal.value = ViewState.Data(users)
        }
    }

    sealed class ViewState {
        object Loading : ViewState()
        data class Data(val userList: List<User>) : ViewState()
    }

    private suspend fun loadUsers(): List<User> {
        return withContext(Dispatchers.IO) {
            provideApi().getUsers().data
        }
    }

    private fun provideApi(): Api {
        return Retrofit.Builder()
            .client(provideOkHttpClient())
            .baseUrl("https://reqres.in/api/")
            .addConverterFactory(MoshiConverterFactory.create(provideMoshi()))
            .build()
            .create(Api::class.java)
    }

    private fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().build()
    }

    private fun provideMoshi(): Moshi {
        return Moshi.Builder().build()
    }
}