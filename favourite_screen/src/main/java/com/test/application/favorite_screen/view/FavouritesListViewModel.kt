package com.test.application.favorite_screen.view

import com.test.application.core.domain.product.Product
import com.test.application.core.repository.FavouritesRepository
import com.test.application.core.utils.AppState
import com.test.application.core.view.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouritesListViewModel @Inject constructor(
    private val favouritesRepository: FavouritesRepository
) : BaseViewModel<AppState>() {

    private val _stateFlow = MutableStateFlow<AppState>(AppState.Loading)
    val stateFlow: StateFlow<AppState> get() = _stateFlow

    private val _isFavourite = MutableStateFlow<Map<String, Boolean>>(emptyMap())
    val isFavourite: StateFlow<Map<String, Boolean>> get() = _isFavourite

    init {
        getAllFavouritesProduct()
        observeFavouritesChanges()
    }

    private fun getAllFavouritesProduct() {
        _stateFlow.value = AppState.Loading
        viewModelCoroutineScope.launch {
            try {
                favouritesRepository.getFavouriteProducts().collect { favouriteProducts ->
                    _stateFlow.value = AppState.Success(favouriteProducts)
                }
            } catch (e: Throwable) {
                _stateFlow.emit(AppState.Error(e))
            }
        }
    }

    private fun observeFavouritesChanges() {
        viewModelCoroutineScope.launch {
            favouritesRepository.getFavouriteProductIds().collect { favouriteIds ->
                val favouritesMap = favouriteIds.associateWith { true }
                _isFavourite.value = favouritesMap
            }
        }
    }

    fun saveFavoriteItem (product: Product) {
        viewModelCoroutineScope.launch(Dispatchers.IO) {
            favouritesRepository.saveFavoriteItem(product)
        }
    }

    fun deleteFavoriteItem (id: String) {
        viewModelCoroutineScope.launch(Dispatchers.IO) {
            favouritesRepository.deleteFavoriteItem(id)
        }
    }
}