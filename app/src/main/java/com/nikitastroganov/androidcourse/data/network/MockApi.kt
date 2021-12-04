package com.nikitastroganov.androidcourse.data.network

import com.haroldadmin.cnradapter.NetworkResponse
import com.nikitastroganov.androidcourse.data.network.request.CreateProfileRequest
import com.nikitastroganov.androidcourse.data.network.request.RefreshAuthTokensRequest
import com.nikitastroganov.androidcourse.data.network.request.SignInWithEmailRequest
import com.nikitastroganov.androidcourse.data.network.response.VerificationTokenResponse
import com.nikitastroganov.androidcourse.data.network.response.error.*
import com.nikitastroganov.androidcourse.entity.AuthTokens
import com.nikitastroganov.androidcourse.entity.Post
import com.nikitastroganov.androidcourse.entity.User

class MockApi : Api {

    override suspend fun getUsers(): NetworkResponse<List<User>, Unit> {
        return NetworkResponse.Success(
            body = listOf(
                User(
                    id = 0,
                    userName = "mock-user",
                    firstName = "mock-user",
                    lastName = "mock-user",
                    avatarUri = "https://reqres.in/img/faces/1-image.jpg",
                    groupName = null
                )
            ),
            code = 200
        )
    }

    override suspend fun signInWithEmail(request: SignInWithEmailRequest): NetworkResponse<AuthTokens, SignInWithEmailErrorResponse> {
        return NetworkResponse.Success(
            AuthTokens(
                accessToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJsb2dnZWRJbkFzIjoiYWRtaW4iLCJpYXQiOjE0MjI3Nzk2MzgsImV4cCI6MTY0MDg3MTc3MX0.gzSraSYS8EXBxLN_oWnFSRgCzcmJmMjLiuyu5CSpyHI",
                refreshToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJsb2dnZWRJbkFzIjoiYWRtaW4iLCJpYXQiOjE0MjI3Nzk2MzgsImV4cCI6MTY0MDg3MTc3MX0.gzSraSYS8EXBxLN_oWnFSRgCzcmJmMjLiuyu5CSpyHI",
                accessTokenExpiration = 1640871771000,
                refreshTokenExpiration = 1640871771000,
            ),
            code = 200
        )
    }

    override suspend fun refreshAuthTokens(request: RefreshAuthTokensRequest): NetworkResponse<AuthTokens, RefreshAuthTokensErrorResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun sendRegistrationVerificationCode(email: String): NetworkResponse<Unit, SendRegistrationVerificationCodeErrorResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun verifyRegistrationCode(
        code: String,
        email: String
    ): NetworkResponse<VerificationTokenResponse, VerifyRegistrationCodeErrorResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun createProfile(request: CreateProfileRequest): NetworkResponse<AuthTokens, CreateProfileErrorResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun getPosts(): NetworkResponse<List<Post>, Unit> {
        TODO("Not yet implemented")
    }
}