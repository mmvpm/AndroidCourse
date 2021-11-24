package com.nikitastroganov.androidcourse.data.network.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class VerificationTokenResponse(
    @Json(name = "verification_token") val verificationToken: String
)