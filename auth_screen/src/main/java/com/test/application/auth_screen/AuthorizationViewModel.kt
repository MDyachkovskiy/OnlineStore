package com.test.application.auth_screen

import androidx.lifecycle.ViewModel
import com.test.application.core.domain.auth.UserLogin
import com.test.application.core.repository.AuthDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthorizationViewModel @Inject constructor(
    private val authDataRepository: AuthDataRepository
) : ViewModel() {

    private val viewModelCoroutineScope = CoroutineScope(
        Dispatchers.IO + SupervisorJob()
    )

    fun saveAuthData(name: String, secondName: String, phoneNumber: String) {
        val userInfo = UserLogin(name=name, secondName=secondName, phoneNumber = phoneNumber)
        viewModelCoroutineScope.launch {
            authDataRepository.saveAuthData(userInfo)
        }
    }

    override fun onCleared() {
        cancelJob()
        super.onCleared()
    }

    private fun cancelJob() {
        viewModelCoroutineScope.coroutineContext.cancelChildren()
    }
}