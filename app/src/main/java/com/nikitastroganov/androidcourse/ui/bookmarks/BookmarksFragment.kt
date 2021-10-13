package com.nikitastroganov.androidcourse.ui.bookmarks

import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.nikitastroganov.androidcourse.R
import com.nikitastroganov.androidcourse.databinding.FragmentBookmarksBinding
import com.nikitastroganov.androidcourse.ui.base.BaseFragment

class BookmarksFragment : BaseFragment(R.layout.fragment_bookmarks) {

    private val viewBinding by viewBinding(FragmentBookmarksBinding::bind)

    private val viewModel: BookmarksViewModel by viewModels()
}