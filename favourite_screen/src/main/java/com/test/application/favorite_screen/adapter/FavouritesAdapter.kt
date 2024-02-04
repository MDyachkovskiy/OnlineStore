package com.test.application.favorite_screen.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.test.application.core.domain.product.Price
import com.test.application.core.domain.product.Product
import com.test.application.core.utils.image_slider.ImageSliderManager
import com.test.application.favorite_screen.databinding.ItemProductBinding

class FavouritesAdapter : RecyclerView.Adapter<FavouritesAdapter.ViewHolder>() {

    private var productsList: MutableList<Product> = mutableListOf()

    var rootListener: ((id: String) -> Unit)? = null
    var favouriteListener: ((product: Product, isFavourite: Boolean) -> Unit)? = null

    fun updateContacts(newProducts: List<Product>) {
        val diffCallback = ProductsDiffUtils(productsList, newProducts)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        productsList.clear()
        productsList.addAll(newProducts)
        diffResult.dispatchUpdatesTo(this)
    }
    fun updateFavourites(favourites: Map<String, Boolean>) {
        productsList = productsList.map { product ->
            product.copy(isFavourite = favourites[product.id] ?: false)
        }.toMutableList()
        updateContacts(productsList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouritesAdapter.ViewHolder {
        val binding = ItemProductBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavouritesAdapter.ViewHolder, position: Int) {
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
            with(binding.cbFavourite) {
                setOnCheckedChangeListener(null)
                isChecked = product.isFavourite
                setOnCheckedChangeListener { _, isChecked ->
                    favouriteListener?.invoke(product, isChecked)
                }
            }
        }

        private fun setTextData(product: Product) {
            setPriceData(product.price)
            setProductTitle(product)
            setRatingData(product)
        }

        private fun setRatingData(product: Product) {
            with(binding) {
                if(product.feedback.count > 0) {
                    ratingBlock.visibility = View.VISIBLE
                    tvProductRating.text = product.feedback.rating.toString()
                    tvRatingCount.text = root.context.getString(
                        com.test.application.core.R.string.reviews_format,
                        product.feedback.count)
                } else{
                    ratingBlock.visibility = View.GONE
                }
            }
        }

        private fun setProductTitle(product: Product) {
            with(binding){
                tvProductName.text = product.title
                tvProductDescription.text = product.title
            }
        }

        private fun setPriceData(price: Price) {
            with(binding) {
                tvOldPrice.text = root.context
                    .getString(com.test.application.core.R.string.formatted_price,
                        price.price, price.unit)

                tvPriceWithDiscount.text = root.context
                    .getString(com.test.application.core.R.string.formatted_price,
                        price.priceWithDiscount,price.unit)

                tvDiscount.text = root.context
                    .getString(com.test.application.core.R.string.discount_format,
                        price.discount)
            }
        }
    }
}