package com.nikitastroganov.androidcourse

import androidx.fragment.app.Fragment
import timber.log.Timber

fun Fragment.logFragmentHierarchy(tag: String? = null) {
    if (BuildConfig.DEBUG) {
        StringBuilder("Nested fragments: ").let {
            fragmentHierarchy(it)
            val timber =
                if (tag == null) Timber.asTree()
                else Timber.tag(tag)
            timber.d(it.toString())
        }
    }
}

private fun Fragment.getNameWithArgs(stringBuilder: StringBuilder) {
    stringBuilder.append(javaClass.simpleName)
    arguments
        ?.toString()
        ?.removePrefix("Bundle[{")
        ?.removeSuffix("}]")
        ?.also { stringBuilder.append("($it)") }
}

private fun Fragment.fragmentHierarchy(stringBuilder: StringBuilder) {
    parentFragment.let { parentFragment ->
        if (parentFragment != null) {
            parentFragment.fragmentHierarchy(stringBuilder)
            stringBuilder.append(" -> ")
        } else {
            activity?.let { activity ->
                stringBuilder.append("${activity.javaClass.simpleName} -> ")
            }
        }
    }
    getNameWithArgs(stringBuilder)
    if (childFragmentManager.backStackEntryCount > 0) {
        stringBuilder.append(" (stack size: ${childFragmentManager.backStackEntryCount})")
    }
}