package com.test.application.catalogue_screen.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.test.application.catalogue_screen.R
import com.test.application.catalogue_screen.databinding.ItemProductBinding
import com.test.application.core.domain.product.Price
import com.test.application.core.domain.product.Product
import com.test.application.core.utils.image_slider.ImageSliderManager

class ProductsAdapter : RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {
    interface ScrollToTopListener {
        fun onScrollToTop()
    }

    private var productsList: MutableList<Product> = mutableListOf()

    var rootListener: ((id: String) -> Unit)? = null
    var favouriteListener: ((product: Product, isFavourite: Boolean) -> Unit)? = null
    var scrollToTopListener: ScrollToTopListener? = null

    fun updateContacts(newProducts: List<Product>, scrollToTop: Boolean = true) {
        val diffCallback = ProductsDiffUtils(productsList, newProducts)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        productsList.clear()
        productsList.addAll(newProducts)
        diffResult.dispatchUpdatesTo(this)
        if (scrollToTop) {
            scrollToTopListener?.onScrollToTop()
        }
    }

    fun updateFavourites(favourites: Map<String, Boolean>) {
        productsList = productsList.map { product ->
            product.copy(isFavourite = favourites[product.id] ?: false)
        }.toMutableList()
        updateContacts(productsList, scrollToTop = false)
    }

    fun getProducts(): List<Product> {
        return productsList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsAdapter.ViewHolder {
        val binding = ItemProductBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductsAdapter.ViewHolder, position: Int) {
        holder.bind(productsList[position])
    }

    override fun getItemCount() = productsList.size

    inner class ViewHolder(
        private val binding: ItemProductBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product) {
            setTextData(product)
            setFavouriteCheckBox(product)
            setImageSlider(product.imageResIds)

            binding.root.setOnClickListener {
                rootListener?.invoke(product.id)
            }
        }

        private fun setImageSlider(imageResIds: List<Int>) {
            val context = binding.root.context
            val imageSliderManager = ImageSliderManager(
                context,
                binding.productImageCarousel,
                binding.pagination
            )
            imageSliderManager.setupSlider(imageResIds)
        }

        private fun setFavouriteCheckBox(product: Product) {
            val favouriteIcon =
                if (product.isFavourite) R.drawable.ic_favorite_fill else R.drawable.ic_favorite_empty
            binding.ivFavourite.setImageResource(favouriteIcon)

            binding.ivFavourite.setOnClickListener {
                val isNowFavourite = !product.isFavourite
                product.isFavourite = isNowFavourite

                val newIcon =
                    if (isNowFavourite) R.drawable.ic_favorite_fill else R.drawable.ic_favorite_empty
                binding.ivFavourite.setImageResource(newIcon)

                favouriteListener?.invoke(product, isNowFavourite)
            }
        }

            private fun setTextData(product: Product) {
                setPriceData(product.price)
                setProductTitle(product)
                setRatingData(product)
            }

            private fun setRatingData(product: Product) {
                with(binding) {
                    if (product.feedback.count > 0) {
                        ratingBlock.visibility = View.VISIBLE
                        tvProductRating.text = product.feedback.rating.toString()
                        tvRatingCount.text = root.context.getString(
                            com.test.application.core.R.string.reviews_format,
                            product.feedback.count
                        )
                    } else {
                        ratingBlock.visibility = View.GONE
                    }
                }
            }

            private fun setProductTitle(product: Product) {
                with(binding) {
                    tvProductName.text = product.title
                    tvProductDescription.text = product.subtitle
                }
            }

            private fun setPriceData(price: Price) {
                with(binding) {
                    tvOldPrice.text = root.context
                        .getString(
                            com.test.application.core.R.string.formatted_price,
                            price.price, price.unit
                        )

                    tvPriceWithDiscount.text = root.context
                        .getString(
                            com.test.application.core.R.string.formatted_price,
                            price.priceWithDiscount, price.unit
                        )

                    tvDiscount.text = root.context
                        .getString(
                            com.test.application.core.R.string.discount_format,
                            price.discount
                        )
                }
            }
        }
    }