package com.nikitastroganov.androidcourse.ui.onboarding

import com.nikitastroganov.androidcourse.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor() : BaseViewModel() {
    var viewPagerPage = 0
    var autoScrollIndex = 0
    var isScrolled = true
}