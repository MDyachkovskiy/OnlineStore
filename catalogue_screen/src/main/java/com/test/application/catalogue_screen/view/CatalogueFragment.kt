package com.test.application.catalogue_screen.view

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.test.application.catalogue_screen.adapter.ProductsAdapter
import com.test.application.catalogue_screen.databinding.FragmentCatalogueBinding
import com.test.application.catalogue_screen.utils.sorting.SortingManager
import com.test.application.catalogue_screen.utils.sorting.SortingOption
import com.test.application.catalogue_screen.utils.tags.TagsManager
import com.test.application.core.domain.product.Product
import com.test.application.core.navigation.OpenProductDetails
import com.test.application.core.utils.AppState
import com.test.application.core.utils.PRODUCT_BUNDLE_KEY
import com.test.application.core.view.BaseFragmentWithAppState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CatalogueFragment : BaseFragmentWithAppState<AppState, List<Product>, FragmentCatalogueBinding>(
    FragmentCatalogueBinding::inflate
) {

    private val viewModel: CatalogueViewModel by activityViewModels()
    private val productsAdapter: ProductsAdapter by lazy { ProductsAdapter() }
    private lateinit var sortingManager: SortingManager
    private lateinit var tagsManager: TagsManager
    private var originalProductsList: List<Product> = listOf()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        observeFavourites()
        initRecyclerView(emptyList())
        setSortingSpinner()
        setTagsFilter()
    }

    private fun setTagsFilter() {
        tagsManager = TagsManager(requireContext(), binding.chipTagFilters) { selectedTag ->
            val filteredProducts = tagsManager.filterProductsByTag(originalProductsList, selectedTag)
            productsAdapter.updateContacts(filteredProducts)
        }
        val tags = resources.getStringArray(com.test.application.core.R.array.tags_array)
        tagsManager.setupTags(tags)
    }

    private fun setSortingSpinner() {
        sortingManager = SortingManager(productsAdapter)
        binding.sortSpinner.mySpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    val selectedOption = when (p2) {
                        0 -> SortingOption.BY_POPULARITY
                        1 -> SortingOption.BY_PRICE_DESC
                        2 -> SortingOption.BY_PRICE_ASC
                        else -> throw IllegalArgumentException(
                            getString(com.test.application.core.R.string.unknown_sorting_option))
                    }
                    sortingManager.onSortingOptionSelected(selectedOption)
                }
                override fun onNothingSelected(p0: AdapterView<*>?) {}
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

    override fun setupData(data: List<Product>) {
        originalProductsList = data
        initRecyclerView(data)
    }

    private fun initRecyclerView(data: List<Product>) {
        productsAdapter.updateContacts(data)
        binding.rvProductsCatalogue.adapter = productsAdapter
        binding.rvProductsCatalogue.layoutManager = GridLayoutManager(requireContext(), 2)
        handleFavouritesCheck()
        handleRootClickListener()
    }

    private fun handleRootClickListener() {
        productsAdapter.rootListener = { id ->
            val bundle = bundleOf(PRODUCT_BUNDLE_KEY to id)
            (activity as OpenProductDetails).openProductDetails(bundle)
        }
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

    private fun initViewModel() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.stateFlow.collect {appState ->
                    renderData(appState)
                }
            }
        }
        requestData()
    }

    private fun requestData() {
        viewModel.getProductsFromRemoteSource()
    }
}