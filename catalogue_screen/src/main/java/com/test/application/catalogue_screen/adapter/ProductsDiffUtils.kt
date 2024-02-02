package com.test.application.catalogue_screen.adapter

import androidx.recyclerview.widget.DiffUtil
import com.test.application.core.domain.product.Product

class ProductsDiffUtils(
    private val oldList: List<Product>,
    private val newList: List<Product>
) : DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}