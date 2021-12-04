package com.nikitastroganov.androidcourse.interactor

import com.haroldadmin.cnradapter.NetworkResponse
import com.nikitastroganov.androidcourse.entity.User
import javax.inject.Inject

class UsersInteractor @Inject constructor(
    private val usersRepository: UsersRepository
) {

    suspend fun loadUsers(): NetworkResponse<List<User>, Unit> =
        usersRepository.getUsers()
}