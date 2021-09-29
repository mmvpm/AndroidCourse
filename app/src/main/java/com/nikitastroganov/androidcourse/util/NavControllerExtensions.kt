package com.nikitastroganov.androidcourse

import androidx.navigation.NavController
import timber.log.Timber

fun NavController.logBackstack(tag: String? = null) {
    val timber =
        if (tag == null) Timber.asTree()
        else Timber.tag(tag)
    timber.d(backQueue.joinToString(" -> ", "Backstack: ") { it.destination.displayName })
}