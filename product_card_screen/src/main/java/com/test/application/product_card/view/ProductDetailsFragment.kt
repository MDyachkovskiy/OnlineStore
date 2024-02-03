package com.test.application.product_card.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.test.application.catalogue_screen.view.CatalogueViewModel
import com.test.application.core.domain.product.Price
import com.test.application.core.domain.product.Product
import com.test.application.core.utils.PRODUCT_BUNDLE_KEY
import com.test.application.core.utils.image_slider.ImageSliderManager
import com.test.application.core.view.BaseFragment
import com.test.application.product_card.databinding.FragmentProductDetailBinding
import com.test.application.product_card.utils.FeaturesManager
import com.test.application.product_card.utils.ToggleTextVisibilityHelper
import com.test.application.product_card.utils.ToggleViewVisibilityHelper
import com.test.application.product_card.utils.getReviewCountString
import com.test.application.product_card.utils.getStockQuantityString
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailsFragment : BaseFragment<FragmentProductDetailBinding>(
    FragmentProductDetailBinding::inflate
) {
    private val productId: String by lazy {
        arguments?.getString(PRODUCT_BUNDLE_KEY) ?:
        throw IllegalArgumentException(getString(com.test.application.core.R.string.unknown_product_id))
    }

    private val viewModel: CatalogueViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        setupToggleButtons()
    }

    private fun setupToggleButtons() {
        ToggleViewVisibilityHelper(binding.btnShowHideDescription, binding.btnBrand,
            binding.tvProductDescription)
        ToggleTextVisibilityHelper(binding.btnShowHideComposition, binding.tvProductComposition)
    }

    private fun initViewModel() {
        viewModel.getProductDetails(productId).observe(viewLifecycleOwner) { product ->
            setupData(product)
        }
    }

    private fun setupData(product: Product?) {
        if (product != null) {
            setImageSlider(product.imageResIds)
            setFavouriteCheckBox(product)
            setTextData(product)
        }
    }

    private fun setFeaturesBlock(product: Product) {
        val featuresManager = FeaturesManager(requireContext(), binding.featuresBlock)
        featuresManager.addFeatures(product.info)
    }

    private fun setTextData(product: Product) {
        setProductMainTitle(product)
        setProductRating(product)
        setProductPrice(product.price)
        setFeaturesBlock(product)
    }

    private fun setProductPrice(price: Price) {
        val actualPrice = getString(com.test.application.core.R.string.formatted_price,
            price.priceWithDiscount,price.unit)
        val oldPrice = getString(com.test.application.core.R.string.formatted_price,
            price.price, price.unit)

        with(binding.priceBlock) {
            tvActualPrice.text = actualPrice
            tvDiscount.text = getString(com.test.application.core.R.string.discount_format,
                price.discount)
            tvOldPrice.text = oldPrice
        }
        with(binding) {
            tvBtnOldPrice.text = oldPrice
            tvBtnProductPrice.text = actualPrice
        }
    }

    private fun setProductRating(product: Product?) {
        if(product?.available != null) {
            with(binding.ratingBlock) {
                tvRating.text = product.feedback.rating.toString()
                tvReviewCount.text = getReviewCountString(requireContext(), product.feedback.count)
            }
        }
    }

    private fun setProductMainTitle(product: Product?) {
        with(binding) {
            if (product != null) {
                tvTitle.text = product.title
                tvSubtitle.text = product.subtitle
                tvAvailableStock.text = getStockQuantityString(requireContext(), product.available)
                btnBrand.text = product.title
                tvProductDescription.text = product.description
                tvProductComposition.text = product.ingredients
            }
        }
    }

    private fun setImageSlider(imageResIds: List<Int>) {
        val imageSliderManager = ImageSliderManager(
            requireContext(),
            binding.productImageCarousel,
            binding.pagination
        )
        imageSliderManager.setupSlider(imageResIds)
    }

    private fun setFavouriteCheckBox(product: Product) {
        with(binding.cbFavourite) {
            setOnCheckedChangeListener(null)
            isChecked = product.isFavourite
            setOnCheckedChangeListener { _, isChecked ->
                if(isChecked) {
                    viewModel.saveFavoriteItem(product)
                } else {
                    viewModel.deleteFavoriteItem(product.id)
                }
            }
        }
    }

}