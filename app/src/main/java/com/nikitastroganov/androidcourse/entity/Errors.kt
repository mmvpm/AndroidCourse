package com.nikitastroganov.androidcourse.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Error(
    @Json(name = "code") val code: ErrorCode,
    @Json(name = "message") val message: String
)

enum class ErrorCode {
    UNKNOWN,
    @Json(name = "required") REQUIRED,
    @Json(name = "null") NULL,
    @Json(name = "invalid") INVALID,
    @Json(name = "blank") BLANK,
    @Json(name = "max_length") MAX_LENGTH,
    @Json(name = "min_length") MIN_LENGTH,
    @Json(name = "password_too_short") PASSWORD_TOO_SHORT,
    @Json(name = "invalid_code") INVALID_CODE,
}