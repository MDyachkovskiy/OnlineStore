package com.test.application.onlinestore.view

import androidx.lifecycle.ViewModel
import com.test.application.core.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {
    fun isUserLoggedIn(): Flow<Boolean> = userRepository.isUserLoggedIn()

}