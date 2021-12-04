package com.nikitastroganov.androidcourse.ui.emailconfirmation

import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.nikitastroganov.androidcourse.R
import com.nikitastroganov.androidcourse.databinding.ViewVerificationCodeEditTextBinding
import java.lang.Math.min

class VerificationCodeEditText @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr, defStyleRes) {

    private val viewBinding =
        ViewVerificationCodeEditTextBinding.inflate(LayoutInflater.from(context), this)

    private val slotViews: List<VerificationCodeSlotView> =
        listOf(
            viewBinding.slot1,
            viewBinding.slot2,
            viewBinding.slot3,
            viewBinding.slot4,
            viewBinding.slot5,
            viewBinding.slot6
        )

    var numberOfSlots = 6

    private val slotValues: Array<CharSequence?> = Array(numberOfSlots) { null }

    var onVerificationCodeFilledListener: (String) -> Unit = {}

    var onVerificationCodeFilledChangeListener: (Boolean) -> Unit = {}

    init {
        viewBinding.realVerificationCodeEditText.addTextChangedListener(

            object : TextWatcher {

                private var wasClearedLastSlot = false

                override fun beforeTextChanged(
                    s: CharSequence,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                    wasClearedLastSlot =
                        !wasClearedLastSlot && start + before == slotViews.size && count == 0
                }

                override fun afterTextChanged(s: Editable) {
                    slotValues.fillWith(s)
                    slotViews.render(slotValues)
                    slotViews.moveCursorToFirstEmptySlot(slotValues)
                    val filled = slotValues.isFilled()
                    onVerificationCodeFilledChangeListener(filled)
                    if (filled) onVerificationCodeFilledListener(slotValues.toCodeString())
                    // Uncomment if we need to clear the whole field on backspace.
                    // if (wasClearedLastSlot) viewBinding.realVerificationCodeEditText.setText("")
                }
            }
        )
        viewBinding.realVerificationCodeEditText.setOnFocusChangeListener { _, focused ->
            if (focused) {
                slotViews.moveCursorToFirstEmptySlot(slotValues)
            }
        }
        slotViews.forEach {
            (it.viewBinding.cursorView.background as AnimationDrawable).start()
        }
        slotValues.fillWith(viewBinding.realVerificationCodeEditText.text)
        slotViews.render(slotValues)

        context
            .theme
            .obtainStyledAttributes(
                attrs,
                R.styleable.VerificationCodeEditText,
                defStyleAttr,
                defStyleRes
            )
            .apply {
                numberOfSlots = getInt(R.styleable.VerificationCodeEditText_vcet_numberOfSlots, 6)
            }
    }

    fun getCode(): String {
        return slotValues.toCodeString()
    }

    fun clear() {
        viewBinding.realVerificationCodeEditText.setText("")
    }

    fun isFilled(): Boolean =
        slotValues.isFilled()

    private fun List<VerificationCodeSlotView>.render(values: Array<CharSequence?>) {
        values.forEachIndexed { index, value ->
            val slotView = this[index]
            slotView.viewBinding.slotValueTextView.text = value
            slotView.viewBinding.root.isActivated = (value != null)
        }
    }

    private fun List<VerificationCodeSlotView>.moveCursorToFirstEmptySlot(values: Array<CharSequence?>) {
        val indexOfFirstEmptySlot = values.indexOfFirst { it == null }
        forEachIndexed { index, slotView ->
            slotView.viewBinding.cursorView.isVisible = (index == indexOfFirstEmptySlot)
        }
    }

    private fun Array<CharSequence?>.fillWith(s: Editable?): Array<CharSequence?> {
        if (s.isNullOrEmpty()) {
            fill(null)
            return this
        }
        val lowestSize = min(size, s.length)
        var i = 0
        while (i < lowestSize) {
            this[i] = s.subSequence(i, i + 1)
            ++i
        }
        if (i < size) {
            fill(null, i, size)
        }
        return this
    }


    private fun Array<CharSequence?>.isFilled(): Boolean =
        all { it != null }

    private fun Array<CharSequence?>.toCodeString(): String =
        joinToString(separator = "", prefix = "", postfix = "", limit = -1, truncated = "") {
            it ?: ""
        }
}