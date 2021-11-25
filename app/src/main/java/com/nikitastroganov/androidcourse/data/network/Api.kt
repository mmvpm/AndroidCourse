package com.nikitastroganov.androidcourse

import com.haroldadmin.cnradapter.NetworkResponse
import com.nikitastroganov.androidcourse.data.network.request.CreateProfileRequest
import com.nikitastroganov.androidcourse.data.network.request.RefreshAuthTokensRequest
import com.nikitastroganov.androidcourse.data.network.request.SignInWithEmailRequest
import com.nikitastroganov.androidcourse.data.network.response.GetUsersResponse
import com.nikitastroganov.androidcourse.data.network.response.VerificationTokenResponse
import com.nikitastroganov.androidcourse.data.network.response.error.*
import com.nikitastroganov.androidcourse.entity.AuthTokens
import com.nikitastroganov.androidcourse.entity.Post
import retrofit2.http.*

interface Api {

    @GET("users?per_page=10")
    suspend fun getUsers(): GetUsersResponse

    @POST("auth/sign-in-email")
    suspend fun signInWithEmail(
        @Body request: SignInWithEmailRequest
    ): NetworkResponse<AuthTokens, SignInWithEmailErrorResponse>

    @POST("auth/refresh-tokens")
    suspend fun refreshAuthTokens(
        @Body request: RefreshAuthTokensRequest
    ): NetworkResponse<AuthTokens, RefreshAuthTokensErrorResponse>

    @POST("registration/verification-code/send")
    suspend fun sendRegistrationVerificationCode(
        @Query("email") email: String,
    ): NetworkResponse<Unit, SendRegistrationVerificationCodeErrorResponse>

    @POST("registration/verification-code/verify")
    suspend fun verifyRegistrationCode(
        @Query("code") code: String,
        @Query("email") email: String?,
        @Query("phone_number") phoneNumber: String?
    ): NetworkResponse<VerificationTokenResponse, VerifyRegistrationCodeErrorResponse>

    @PUT("registration/create-profile")
    suspend fun createProfile(
        @Body request: CreateProfileRequest
    ): NetworkResponse<AuthTokens, CreateProfileErrorResponse>

    @POST("posts")
    suspend fun getPosts(): NetworkResponse<List<Post>, Unit>
}

//@JsonClass(generateAdapter = true)
//data class GetUsersResponse(
//    @Json(name = "data") val data: List<User>
//)
