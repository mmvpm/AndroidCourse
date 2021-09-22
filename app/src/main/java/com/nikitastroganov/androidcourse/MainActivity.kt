package com.nikitastroganov.androidcourse

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.nikitastroganov.androidcourse.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity(R.layout.activity_main) {

    companion object {
        const val LOG_TAG = "MainActivity"
    }

    private val viewModel: MainViewModel by viewModels()

    private val viewBinding by viewBinding(ActivityMainBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(LOG_TAG, "onCreate()")

        switchLoadingButton(true)
        setupRecyclerView(viewBinding.usersRecyclerView)

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.viewState.collect { viewState ->
                    Log.d(LOG_TAG, "Users were received: $viewState")
                    renderViewState(viewState)
                }
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

    @SuppressLint("NotifyDataSetChanged")
    private fun renderViewState(viewState: MainViewModel.ViewState) {
        when (viewState) {
            is MainViewModel.ViewState.Loading -> {
                switchLoadingButton(true)
            }
            is MainViewModel.ViewState.Data -> {
                (viewBinding.usersRecyclerView.adapter as UserAdapter).apply {
                    userList = viewState.userList
                    notifyDataSetChanged()
                }
                switchLoadingButton(false)
            }
        }
    }
}