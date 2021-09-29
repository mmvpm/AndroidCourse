package com.nikitastroganov.androidcourse.data.network.response

import com.nikitastroganov.androidcourse.entity.User
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetUsersResponse(
    @Json(name = "data") val data: List<User>
)