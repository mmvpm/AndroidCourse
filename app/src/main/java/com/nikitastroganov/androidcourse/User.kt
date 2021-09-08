package com.nikitastroganov.androidcourse

import android.net.Uri

data class User(
    val avatarUri: Uri,
    val userName: String,
    val groupName: String
)
