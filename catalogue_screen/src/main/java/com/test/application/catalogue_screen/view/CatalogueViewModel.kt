package com.test.application.catalogue_screen.view

import com.test.application.core.domain.product.Product
import com.test.application.core.repository.CatalogueInteractor
import com.test.application.core.utils.AppState
import com.test.application.core.view.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatalogueViewModel @Inject constructor(
    private val interactor: CatalogueInteractor
) : BaseViewModel<AppState>() {

    private val _stateFlow = MutableStateFlow<AppState>(AppState.Loading)
    val stateFlow: StateFlow<AppState> get() = _stateFlow

    private val _isFavourite = MutableStateFlow<Map<String, Boolean>>(emptyMap())
    val isFavourite: StateFlow<Map<String, Boolean>> get() = _isFavourite

    fun getProductsFromRemoteSource() {
        _stateFlow.value = AppState.Loading
        viewModelCoroutineScope.launch {
            try {
                val products = interactor.getProducts().first()
                val productIds = products.map { it.id }
                interactor.checkFavoriteItems(productIds).collect { favoritesStatus ->
                    val updatedProducts = products.map { product ->
                        product.copy(isFavourite = favoritesStatus[product.id] ?: false)
                    }
                    _stateFlow.value = AppState.Success(updatedProducts)
                }
            } catch (e:Throwable) {
                _stateFlow.emit(AppState.Error(e))
            }
        }
    }

    fun saveFavoriteItem (product: Product) {
        viewModelCoroutineScope.launch(Dispatchers.IO) {
            interactor.saveFavoriteItem(product)
        }
    }

    fun deleteFavoriteItem (id: String) {
        viewModelCoroutineScope.launch(Dispatchers.IO) {
            interactor.deleteFavoriteItem(id)
        }
    }
}