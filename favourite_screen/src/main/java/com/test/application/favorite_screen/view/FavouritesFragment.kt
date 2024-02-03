package com.test.application.favorite_screen.view

import com.test.application.core.domain.product.Product
import com.test.application.core.utils.AppState
import com.test.application.core.view.BaseFragmentWithAppState
import com.test.application.favorite_screen.databinding.FragmentFavouritesBinding

class FavouritesFragment: BaseFragmentWithAppState<AppState, List<Product>, FragmentFavouritesBinding>(
    FragmentFavouritesBinding::inflate
){
    override fun setupData(data: List<Product>) {
        TODO("Not yet implemented")
    }
}