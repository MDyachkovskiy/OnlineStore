package com.test.application.favorite_screen.view

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.test.application.core.domain.product.Product
import com.test.application.core.navigation.OpenProductDetailsFromFavourites
import com.test.application.core.utils.AppState
import com.test.application.core.utils.PRODUCT_BUNDLE_KEY
import com.test.application.core.view.BaseFragmentWithAppState
import com.test.application.favorite_screen.adapter.FavouritesAdapter
import com.test.application.favorite_screen.databinding.FragmentFavouritesListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavouritesListFragment : BaseFragmentWithAppState<AppState, List<Product>, FragmentFavouritesListBinding>(
    FragmentFavouritesListBinding::inflate
) {

    private val viewModel: FavouritesListViewModel by viewModels()
    private val productsAdapter: FavouritesAdapter by lazy { FavouritesAdapter() }
    private var openProductDetailsListener: OpenProductDetailsFromFavourites? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OpenProductDetailsFromFavourites) {
            openProductDetailsListener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        openProductDetailsListener = null
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeFavourites()
        initViewModel()
    }

    private fun initViewModel() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.stateFlow.collect { appState ->
                    renderData(appState)
                }
            }
        }
    }

    override fun setupData(data: List<Product>) {
        initRecyclerView(data)
    }

    private fun initRecyclerView(data: List<Product>) {
        productsAdapter.updateContacts(data)
        binding.rvFavourites.adapter = productsAdapter
        binding.rvFavourites.layoutManager = GridLayoutManager(requireContext(), 2)
        handleFavouritesCheck()
        handleRootClickListener()
    }

    private fun handleFavouritesCheck() {
        productsAdapter.favouriteListener = {product, isFavourite ->
            if(isFavourite) {
                viewModel.saveFavoriteItem(product)
            } else {
                viewModel.deleteFavoriteItem(product.id)
            }
        }
    }

    private fun observeFavourites() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.isFavourite.collect { favourites ->
                    productsAdapter.updateFavourites(favourites)
                }
            }
        }
    }

    private fun handleRootClickListener() {
        productsAdapter.rootListener = { id ->
            val bundle = bundleOf(PRODUCT_BUNDLE_KEY to id)
            openProductDetailsListener?.openProductDetailsFromFavourites(bundle)
        }
    }

    override fun onDestroyView() {
        binding.rvFavourites.adapter = null
        super.onDestroyView()
    }
}