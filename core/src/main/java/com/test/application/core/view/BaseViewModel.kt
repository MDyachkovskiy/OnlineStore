package com.test.application.core.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.application.core.utils.AppState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren

abstract class BaseViewModel<T: AppState>(
    protected val _mutableLiveData: MutableLiveData<T> = MutableLiveData()
) : ViewModel() {

    protected val viewModelCoroutineScope = CoroutineScope(
        Dispatchers.Main + SupervisorJob()
    )

    override fun onCleared() {
        cancelJob()
        super.onCleared()
    }

    private fun cancelJob() {
        viewModelCoroutineScope.coroutineContext.cancelChildren()
    }
}