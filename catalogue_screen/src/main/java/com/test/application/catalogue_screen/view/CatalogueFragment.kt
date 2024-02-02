package com.test.application.catalogue_screen.view

import android.os.Bundle
import android.view.View
import com.test.application.catalogue_screen.databinding.FragmentCatalogueBinding
import com.test.application.core.domain.product.Product
import com.test.application.core.utils.AppState
import com.test.application.core.view.BaseFragmentWithAppState
import dagger.hilt.android.AndroidEntryPoint
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.test.application.catalogue_screen.adapter.ProductsAdapter
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CatalogueFragment : BaseFragmentWithAppState<AppState, List<Product>, FragmentCatalogueBinding>(
    FragmentCatalogueBinding::inflate
) {

    private val viewModel: CatalogueViewModel by viewModels()
    private lateinit var productsAdapter: ProductsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
    }
    override fun setupData(data: List<Product>) {
        initRecyclerView(data)
    }

    private fun initRecyclerView(data: List<Product>) {
        productsAdapter = ProductsAdapter()
        productsAdapter.updateContacts(data)
        binding.rvProductsCatalogue.adapter = productsAdapter
        binding.rvProductsCatalogue.layoutManager = GridLayoutManager(requireContext(), 2)
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