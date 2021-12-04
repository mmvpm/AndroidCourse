package com.nikitastroganov.androidcourse.ui.news

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.nikitastroganov.androidcourse.R
import com.nikitastroganov.androidcourse.databinding.FragmentNewsBinding
import com.nikitastroganov.androidcourse.ui.base.BaseFragment
import dev.chrisbanes.insetter.applyInsetter

class NewsFragment : BaseFragment(R.layout.fragment_news) {

    private val viewBinding by viewBinding(FragmentNewsBinding::bind)

    private val viewModel: NewsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.toolbar.applyInsetter {
            type(statusBars = true) { margin() }
        }
    }
}