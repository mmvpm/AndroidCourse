package com.nikitastroganov.androidcourse.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class Post(@Json(name = "id") val id: Long,
           @Json(name = "link") val linkUrl: String?,
           @Json(name = "image") val imageUrl: String?,
           @Json(name = "title") val title: String?,
           @Json(name = "text") val text: String?,
           @Json(name = "created_at") val createdAt: String,
           @Json(name = "updated_at") val updatedAt: String)