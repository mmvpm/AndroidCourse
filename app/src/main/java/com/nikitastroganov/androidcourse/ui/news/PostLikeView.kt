package com.nikitastroganov.androidcourse.ui.news

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.Gravity
import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.view.isInvisible
import com.nikitastroganov.androidcourse.R
import com.nikitastroganov.androidcourse.util.dpToPx
import com.nikitastroganov.androidcourse.util.getCompatColor
import com.nikitastroganov.androidcourse.util.inflate

class PostLikesView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleRes: Int = 0
) : FrameLayout(context, attrs, defStyleRes) {

    private var textView: TextView

    private var colorRadius: Float = dpToPx(10f)

    var colors: List<Int> = listOf(Color.LTGRAY).flatMap { listOf(it, it, it, it, it, it) }
        .flatMap { listOf(it, it, it, it, it, it) } // 25
        set(value) {
            field = value
            // requestLayout()
            invalidate()
        }

    private var visibleColorCount: Int = 0
    private var collapsedColorCount: Int = 0

    private var circlePaint: Paint =
        Paint().apply {
            strokeWidth = colorRadius
        }
    private var circleBorderPaint: Paint =
        Paint()
            .apply {
                color = context.getCompatColor(R.color.color_post_like_border)
                strokeWidth = dpToPx(2f)
                style = Paint.Style.STROKE
            }

    init {
        setWillNotDraw(false)
        textView = (inflate(R.layout.item_post_remaining_likes_count, false) as TextView)
            .also { textView ->
                addView(
                    textView,
                    LayoutParams(
                        LayoutParams.WRAP_CONTENT,
                        LayoutParams.WRAP_CONTENT,
                        Gravity.END or Gravity.CENTER_VERTICAL
                    )
                )
            }
        if (isInEditMode) {
            colors =
                listOf(Color.LTGRAY)
                    .flatMap { listOf(it, it, it, it, it) } // 5
                    .flatMap { listOf(it, it, it, it, it) } // 25
            setCollapsedColorCountText(colors.size)
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setCollapsedColorCountText(colors.size)
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        if (colors.isNotEmpty()) {
            val widthOfOneColor = (colorRadius * 3).toInt()
            val widthOfAllColors = colors.size * widthOfOneColor
            val widthWithoutPadding = measuredWidth - paddingStart - paddingEnd
            var availableSpace = widthWithoutPadding
            if (availableSpace in 1..widthOfAllColors) {
                var visibleColorCount = availableSpace / widthOfOneColor + 1
                do {
                    visibleColorCount--
                    setCollapsedColorCountText(colors.size - visibleColorCount)
                    measureChild(textView, widthMeasureSpec, heightMeasureSpec)
                    availableSpace = widthWithoutPadding - textView.measuredWidth
                } while (0 < visibleColorCount && availableSpace <= visibleColorCount * widthOfOneColor)
                textView.isInvisible = false
                this.visibleColorCount = visibleColorCount
                collapsedColorCount = colors.size - visibleColorCount
            } else {
                textView.isInvisible = true
                visibleColorCount = colors.size
                collapsedColorCount = 0
            }
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val yPos = height / 2f
        var xPos = paddingStart + colorRadius
        for (i in 0 until visibleColorCount) {
            canvas.drawCircle(xPos, yPos, colorRadius, circleBorderPaint)
            canvas.drawCircle(xPos, yPos, colorRadius, circlePaint.apply { color = colors[i] })
            xPos += (colorRadius * 3)
        }
    }

    private fun setCollapsedColorCountText(count: Int) {
        textView.text = resources.getString(R.string.common_counter_prefix, count)
    }
}