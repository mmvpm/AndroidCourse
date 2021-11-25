package com.nikitastroganov.androidcourse.data.network

import com.haroldadmin.cnradapter.NetworkResponse
import com.nikitastroganov.androidcourse.Api
import com.nikitastroganov.androidcourse.data.network.request.CreateProfileRequest
import com.nikitastroganov.androidcourse.data.network.request.RefreshAuthTokensRequest
import com.nikitastroganov.androidcourse.data.network.request.SignInWithEmailRequest
import com.nikitastroganov.androidcourse.data.network.response.GetUsersResponse
import com.nikitastroganov.androidcourse.data.network.response.VerificationTokenResponse
import com.nikitastroganov.androidcourse.data.network.response.error.*
import com.nikitastroganov.androidcourse.entity.AuthTokens
import com.nikitastroganov.androidcourse.entity.Post

class MockApi : Api {
    override suspend fun getUsers(): GetUsersResponse {
        TODO("Not yet implemented")
    }

    override suspend fun signInWithEmail(request: SignInWithEmailRequest): NetworkResponse<AuthTokens, SignInWithEmailErrorResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun refreshAuthTokens(request: RefreshAuthTokensRequest): NetworkResponse<AuthTokens, RefreshAuthTokensErrorResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun sendRegistrationVerificationCode(email: String): NetworkResponse<Unit, SendRegistrationVerificationCodeErrorResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun verifyRegistrationCode(
        code: String,
        email: String?,
        phoneNumber: String?
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