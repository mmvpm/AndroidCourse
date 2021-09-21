package com.nikitastroganov.androidcourse

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

import com.nikitastroganov.androidcourse.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val viewModel: MainViewModel by viewModels()

    private val viewBinding by viewBinding(ActivityMainBinding::bind)

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        switchLoadingButton(true)

        val adapter = setupRecyclerView(viewBinding.usersRecyclerView)

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.viewState.collect { viewState ->
                    when (viewState) {
                        is MainViewModel.ViewState.Data -> {
                            adapter.userList = viewState.userList
                            switchLoadingButton(false)
                        }
                        is MainViewModel.ViewState.Loading -> {
                            switchLoadingButton(true)
                        }
                    }
                }
                adapter.notifyDataSetChanged()
            }
        }
    }

    private fun switchLoadingButton(loading: Boolean) {
        viewBinding.progressBar.isVisible = loading
        viewBinding.usersRecyclerView.isVisible = !loading
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun setupItemDecoration(recyclerView: RecyclerView) {
        val divider = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        divider.setDrawable(getDrawable(R.drawable.item_user_divider)!!)
        recyclerView.addItemDecoration(divider)
    }

    private fun setupRecyclerView(recyclerView: RecyclerView): UserAdapter {
        val adapter = UserAdapter()
        recyclerView.adapter = adapter
        setupItemDecoration(recyclerView)
        return adapter
    }
}