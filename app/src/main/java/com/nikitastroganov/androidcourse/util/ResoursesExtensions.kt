package com.nikitastroganov.androidcourse.util

import android.content.res.Resources
import android.text.SpannableStringBuilder
import android.text.Spanned
import androidx.annotation.StringRes

/**
 * Fills template string with (possibly) spanned args.
 *
 * @return Spanned string
 */
fun Resources.getSpannedString(@StringRes resId: Int, vararg formatArgs: CharSequence): Spanned {
    var lastArgIndex = 0
    val spannableStringBuilder = SpannableStringBuilder(getString(resId, *formatArgs))
    for (arg in formatArgs) {
        val argString = arg.toString()
        lastArgIndex = spannableStringBuilder.indexOf(argString, lastArgIndex)
        if (lastArgIndex != -1) {
            spannableStringBuilder.replace(lastArgIndex, lastArgIndex + argString.length, arg)
            lastArgIndex += argString.length
        }
    }
    return spannableStringBuilder
}