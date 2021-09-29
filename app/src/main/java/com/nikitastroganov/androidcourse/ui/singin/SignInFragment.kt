package com.nikitastroganov.androidcourse.ui.singin

import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.nikitastroganov.androidcourse.ui.base.BaseFragment
import com.nikitastroganov.androidcourse.R
import com.nikitastroganov.androidcourse.ui.signup.SignUpViewModel
import com.nikitastroganov.androidcourse.databinding.FragmentSignInBinding

class SignInFragment : BaseFragment(R.layout.fragment_sign_in)  {

    private val viewBinding by viewBinding(FragmentSignInBinding::bind)

    private val viewModel: SignUpViewModel by viewModels()
}