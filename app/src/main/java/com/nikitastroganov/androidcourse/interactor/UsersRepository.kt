package com.nikitastroganov.androidcourse.interactor

import com.haroldadmin.cnradapter.NetworkResponse
import com.nikitastroganov.androidcourse.data.network.MockApi
import com.nikitastroganov.androidcourse.entity.User

class UsersRepository {

    suspend fun getUsers(): NetworkResponse<List<User>, Unit> = MockApi().getUsers()
}