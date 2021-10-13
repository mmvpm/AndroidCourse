package com.nikitastroganov.androidcourse.ui.likes

import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.nikitastroganov.androidcourse.R
import com.nikitastroganov.androidcourse.databinding.FragmentLikesBinding
import com.nikitastroganov.androidcourse.ui.base.BaseFragment

class LikesFragment : BaseFragment(R.layout.fragment_likes) {

    private val viewBinding by viewBinding(FragmentLikesBinding::bind)

    private val viewModel: LikesViewModel by viewModels()
}