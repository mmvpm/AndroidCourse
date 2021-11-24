package com.nikitastroganov.androidcourse.data.network.response.error

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RefreshAuthTokensErrorResponse(
    @Json(name = "non_field_errors") val nonFieldErrors: List<Error>?,
    @Json(name = "refresh") val refresh: List<Error>?
)
