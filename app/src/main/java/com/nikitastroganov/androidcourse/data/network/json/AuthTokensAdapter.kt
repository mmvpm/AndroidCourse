package com.nikitastroganov.androidcourse.data.network.json

import android.util.Base64
import com.nikitastroganov.androidcourse.entity.AuthTokens
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import org.json.JSONObject
import timber.log.Timber

class AuthTokensAdapter : JsonAdapter<AuthTokens>() {

    companion object {

        private const val FIELD_NAME_ACCESS_TOKEN = "access"
        private const val FIELD_NAME_REFRESH_TOKEN = "refresh"

        private val FIELD_NAMES = JsonReader.Options.of(
            FIELD_NAME_ACCESS_TOKEN,  // 0
            FIELD_NAME_REFRESH_TOKEN  // 1
        )
    }

    override fun fromJson(reader: JsonReader): AuthTokens? {

        var accessToken: String? = null
        var accessTokenExpiration: Long? = null
        var refreshToken: String? = null
        var refreshTokenExpiration: Long? = null

        reader.beginObject()
        while (reader.hasNext()) {
            when(reader.selectName(FIELD_NAMES)) {
                0 -> {
                    accessToken = reader.nextString()
                    accessTokenExpiration = getExpiration(accessToken)
                }
                1 -> {
                    refreshToken = reader.nextString()
                    refreshTokenExpiration = getExpiration(refreshToken)
                }
                else -> {
                    reader.skipName()
                    reader.skipValue()
                }
            }
        }
        reader.endObject()

        return AuthTokens(
            accessToken ?: throw JsonDataException("Required property '$FIELD_NAME_ACCESS_TOKEN' missing at ${reader.path}"),
            refreshToken ?: throw JsonDataException("Required property '$FIELD_NAME_REFRESH_TOKEN' missing at ${reader.path}"),
            accessTokenExpiration ?: throw JsonDataException("Required property 'accessTokenExpiration' missing at ${reader.path}"),
            refreshTokenExpiration ?: throw JsonDataException("Required property 'refreshTokenExpiration' missing at ${reader.path}")
        )
    }

    override fun toJson(writer: JsonWriter, authTokens: AuthTokens?) {
        authTokens?.let {
            writer.beginObject()
            writer.name(FIELD_NAME_ACCESS_TOKEN)
            writer.value(it.accessToken)
            writer.name(FIELD_NAME_REFRESH_TOKEN)
            writer.value(it.refreshToken)
            writer.endObject()
        }
    }

    /**
     * JSON Web Token (JWT) payload parsing.
     */
    private fun getExpiration(token: String): Long =
        try {
            val payloadPart = token.split(".")[1]
            val payloadJson = String(Base64.decode(payloadPart, Base64.DEFAULT))
            val payload = JSONObject(payloadJson)
            val exp = payload.getLong("exp")
            exp * 1000
        } catch (e: Exception) {
            Timber.d(e)
            System.currentTimeMillis()
        }
}