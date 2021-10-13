package com.nikitastroganov.androidcourse.ui.main

import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.nikitastroganov.androidcourse.ui.base.BaseFragment
import com.nikitastroganov.androidcourse.R
import com.nikitastroganov.androidcourse.databinding.FragmentMainBinding
import com.nikitastroganov.androidcourse.ui.signup.SignUpViewModel

class MainFragment : BaseFragment(R.layout.fragment_main)  {

    private val viewBinding by viewBinding(FragmentMainBinding::bind)

    private val viewModel: SignUpViewModel by viewModels()
}