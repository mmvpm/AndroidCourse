package com.nikitastroganov.androidcourse

import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.nikitastroganov.androidcourse.databinding.ItemOnboardingTextBinding

fun onboardingTextAdapterDelegate() =
    adapterDelegateViewBinding<String, CharSequence, ItemOnboardingTextBinding>(
        viewBinding = { layoutInflater, parent ->
            ItemOnboardingTextBinding.inflate(layoutInflater, parent, false)
        },
        block = {
            bind {
                binding.textView.text = item
            }
        }
    )