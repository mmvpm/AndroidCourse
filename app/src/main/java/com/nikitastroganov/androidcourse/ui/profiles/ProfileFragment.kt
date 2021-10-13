package com.nikitastroganov.androidcourse.ui.profiles

import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.nikitastroganov.androidcourse.R
import com.nikitastroganov.androidcourse.databinding.FragmentProfileBinding
import com.nikitastroganov.androidcourse.ui.base.BaseFragment

class ProfileFragment : BaseFragment(R.layout.fragment_profile) {

    private val viewBinding by viewBinding(FragmentProfileBinding::bind)

    private val viewModel: ProfileViewModel by viewModels()
}