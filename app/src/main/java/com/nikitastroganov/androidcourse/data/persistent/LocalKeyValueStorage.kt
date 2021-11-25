package com.nikitastroganov.androidcourse.data.persistent

import android.content.SharedPreferences
import androidx.core.content.edit
import com.nikitastroganov.androidcourse.entity.AuthTokens
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.Moshi
import timber.log.Timber
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class LocalKeyValueStorage(
    private val pref: SharedPreferences,
    moshi: Moshi
) {

    var authTokens: AuthTokens?
            by JsonDelegate(
                AUTH_TOKENS,
                pref,
                moshi.adapter(AuthTokens::class.java)
            )

    private class JsonDelegate<T>(
        private val key: String,
        private val pref: SharedPreferences,
        private val adapter: JsonAdapter<T>
    ) : ReadWriteProperty<LocalKeyValueStorage, T?> {

        override fun setValue(thisRef: LocalKeyValueStorage, property: KProperty<*>, value: T?) {
            pref.edit(commit = true) {
                if (value == null) remove(key)
                else putString(key, adapter.toJson(value))
            }
        }

        override fun getValue(thisRef: LocalKeyValueStorage, property: KProperty<*>): T? {
            return try {
                pref.getString(key, null)?.let { adapter.fromJson(it) }
            } catch (e: JsonDataException) {
                Timber.e(e)
                setValue(thisRef, property, null)
                null
            }
        }
    }

    companion object {

        private const val AUTH_TOKENS = "auth_tokens"
    }
} 