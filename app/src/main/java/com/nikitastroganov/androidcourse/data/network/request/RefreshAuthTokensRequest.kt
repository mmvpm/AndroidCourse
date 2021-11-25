package com.nikitastroganov.androidcourse.data.network.request

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RefreshAuthTokensRequest(
    @Json(name = "refresh") val refresh: String
)
