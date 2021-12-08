package com.nikitastroganov.androidcourse.ui.emailconfirmation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.nikitastroganov.androidcourse.R
import com.nikitastroganov.androidcourse.databinding.FragmentEmailConfirmationBinding
import com.nikitastroganov.androidcourse.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import dev.chrisbanes.insetter.applyInsetter

@AndroidEntryPoint
class EmailConfirmationFragment : BaseFragment(R.layout.fragment_email_confirmation) {

    private val viewBinding by viewBinding(FragmentEmailConfirmationBinding::bind)

    private val viewModel: EmailConfirmationViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }
        viewBinding.confirmButton.setOnClickListener {
            viewModel.signIn("", "")
        }
        viewBinding.confirmButton.applyInsetter {
            type(navigationBars = true) { margin() }
        }
        viewBinding.backButton.applyInsetter {
            type(statusBars = true) { margin() }
        }
    }
}