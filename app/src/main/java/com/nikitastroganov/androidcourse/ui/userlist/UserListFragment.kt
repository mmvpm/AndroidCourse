package com.nikitastroganov.androidcourse.ui.userlist

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.nikitastroganov.androidcourse.R
import com.nikitastroganov.androidcourse.databinding.FragmentUserListBinding
import com.nikitastroganov.androidcourse.ui.base.BaseFragment
import dev.chrisbanes.insetter.applyInsetter
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class UserListFragment : BaseFragment(R.layout.fragment_user_list) {

    private val viewModel: UserListViewModel by viewModels()

    private val viewBinding by viewBinding(FragmentUserListBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        switchLoadingButton(true)
        subscribeToViewState()

        viewBinding.usersRecyclerView.applyInsetter {
            type(statusBars = true) { margin() }
        }
    }

    private fun subscribeToViewState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.viewState.collect(::renderViewState)
            }
        }
    }

    private fun switchLoadingButton(loading: Boolean) {
        viewBinding.progressBar.isVisible = loading
        viewBinding.usersRecyclerView.isVisible = !loading
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun setupItemDecoration(recyclerView: RecyclerView) {
        val divider = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        divider.setDrawable(getDrawable(requireContext(), R.drawable.item_user_divider)!!)
        recyclerView.addItemDecoration(divider)
    }

    private fun setupRecyclerView(): UserAdapter =
        UserAdapter().also { adapter ->
            viewBinding.usersRecyclerView.adapter = adapter
            setupItemDecoration(viewBinding.usersRecyclerView)
        }

    @SuppressLint("NotifyDataSetChanged")
    private fun renderViewState(viewState: UserListViewModel.ViewState) {
        when (viewState) {
            is UserListViewModel.ViewState.Loading -> {
                switchLoadingButton(true)
            }
            is UserListViewModel.ViewState.Data -> {
                (viewBinding.usersRecyclerView.adapter as UserAdapter).apply {
                    userList = viewState.userList
                    notifyDataSetChanged()
                }
                switchLoadingButton(false)
            }
        }
    }
}
