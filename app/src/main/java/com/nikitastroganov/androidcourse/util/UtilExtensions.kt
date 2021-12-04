package com.nikitastroganov.androidcourse.util

import android.util.DisplayMetrics
import android.util.TypedValue
import androidx.annotation.Dimension
import androidx.annotation.Dimension.DP

fun dpToPx(displayMetrics: DisplayMetrics, @Dimension(unit = DP) dp: Float) =
    TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dp,
        displayMetrics
    )