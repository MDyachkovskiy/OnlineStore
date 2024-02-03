package com.test.application.catalogue_screen.utils.tags

import android.content.Context
import android.view.LayoutInflater
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.test.application.catalogue_screen.R
import com.test.application.core.domain.product.Product

class TagsManager(
    private val context: Context,
    private val chipGroup: ChipGroup,
    private val onTagSelected: (String?) -> Unit
) {

    fun setupTags(tags: Array<String>) {
        chipGroup.removeAllViews()

        tags.forEach { tag ->
            val chip = LayoutInflater.from(context).inflate(R.layout.custom_chip, chipGroup, false) as Chip
            chip.apply {
                text = tag
                isCheckable = true
                isCheckedIconVisible = false
                setOnCheckedChangeListener { _, isChecked ->
                    if (isChecked) {
                        onTagSelected(tag)
                    }
                }
            }
            chipGroup.addView(chip)
        }
    }

    fun filterProductsByTag(products: List<Product>, selectedTag: String?): List<Product> {
        return if (selectedTag.isNullOrEmpty() ||
            selectedTag == context.getString(com.test.application.core.R.string.tag_all)) {
            products
        } else {
            products.filter { it.tags.contains(selectedTag) }
        }
    }
}