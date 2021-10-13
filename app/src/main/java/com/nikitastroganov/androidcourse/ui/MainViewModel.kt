package com.nikitastroganov.androidcourse.ui

import com.nikitastroganov.androidcourse.ui.base.BaseViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class MainViewModel : BaseViewModel() {

    val isAuthorizedFlow: Flow<Boolean> = MutableStateFlow(true)
}