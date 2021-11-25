package com.nikitastroganov.androidcourse.data.network.response.error

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CreateProfileErrorResponse(
    @Json(name = "non_field_errors") val nonFieldErrors: List<Error>?,
    @Json(name = "verification_token") val verificationToken: List<Error>?,
    @Json(name = "first_name") val firstName: List<Error>?,
    @Json(name = "last_name") val lastName: List<Error>?,
    @Json(name = "email") val email: List<Error>?,
    @Json(name = "password") val password: List<Error>?
)
