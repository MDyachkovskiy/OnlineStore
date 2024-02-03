package com.test.application.account_profile_screen.view

import com.test.application.core.repository.AccountProfileRepository
import com.test.application.core.utils.AppState
import com.test.application.core.view.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@HiltViewModel
class AccountProfileViewModel(
    private val accountProfileRepository: AccountProfileRepository
) : BaseViewModel<AppState>() {

    private val _stateFlow = MutableStateFlow<AppState>(AppState.Loading)
    val stateFlow: StateFlow<AppState> get() = _stateFlow

    fun getUserInfo() {
        _stateFlow.value = AppState.Loading
        viewModelCoroutineScope.launch {
            try {
                val userInfo = accountProfileRepository.getUserInfo().first()
                val favoriteCount = accountProfileRepository.countFavorites()
                val updatedUserInfo = userInfo.copy(favoriteCount = favoriteCount)
                _stateFlow.emit(AppState.Success(updatedUserInfo))
            } catch (e:Throwable) {
                _stateFlow.emit(AppState.Error(e))
            }
        }
    }

    fun logoutUser() {
        viewModelCoroutineScope.launch {
            try {
                accountProfileRepository.clearUserData()
            } catch (e: Throwable) {
                _stateFlow.emit(AppState.Error(e))
            }
        }
    }
}