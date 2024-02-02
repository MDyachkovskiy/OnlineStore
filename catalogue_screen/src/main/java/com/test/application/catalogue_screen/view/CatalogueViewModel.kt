package com.test.application.catalogue_screen.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.test.application.core.repository.CatalogueInteractor
import com.test.application.core.utils.AppState
import com.test.application.core.view.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatalogueViewModel @Inject constructor(
    private val interactor: CatalogueInteractor
) : BaseViewModel<AppState>() {

    private val _stateFlow = MutableStateFlow<AppState>(AppState.Loading)
    val stateFlow: StateFlow<AppState> get() = _stateFlow

    private val _isFavorite = MutableLiveData<Boolean>()
    val isFavorite: LiveData<Boolean> get() = _isFavorite

    fun getProductsFromRemoteSource() {
        _stateFlow.value = AppState.Loading
        viewModelCoroutineScope.launch {
            try {
                val contactsFlow = interactor.getProducts()
                contactsFlow.collect { products ->
                    _stateFlow.value = AppState.Success(products)
                }
            } catch (e:Throwable) {
                _stateFlow.emit(AppState.Error(e))
            }
        }
    }

    fun saveFavoriteItem (id: String) {
        viewModelCoroutineScope.launch(Dispatchers.IO) {
            interactor.saveFavoriteItem(id)
        }
    }

    fun deleteFavoriteItem (id: String) {
        viewModelCoroutineScope.launch(Dispatchers.IO) {
            interactor.deleteFavoriteItem(id)
        }
    }

    fun checkFavoriteItem(id: String) {
        viewModelCoroutineScope.launch(Dispatchers.IO) {
            _isFavorite.postValue(interactor.checkFavoriteItem(id))
        }
    }
}