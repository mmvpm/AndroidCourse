package com.nikitastroganov.androidcourse

import com.nikitastroganov.androidcourse.data.network.response.GetUsersResponse
import retrofit2.http.GET

interface Api {
    @GET("users?per_page=12")
    suspend fun getUsers(): GetUsersResponse
}