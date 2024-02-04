package com.test.application.catalogue_screen.utils.tags

import android.content.Context
import android.view.LayoutInflater
import androidx.core.view.children
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.test.application.catalogue_screen.R
import com.test.application.core.domain.product.Product

class TagsManager(
    private val context: Context,
    private val chipGroup: ChipGroup,
    private val onTagSelected: (String?) -> Unit
) {

    private val tagMapping = mapOf(
        context.getString(com.test.application.core.R.string.tag_face) to
                context.getString(com.test.application.core.R.string.tag_eng_face),
        context.getString(com.test.application.core.R.string.tag_body) to
                context.getString(com.test.application.core.R.string.tag_eng_body),
        context.getString(com.test.application.core.R.string.tag_suntan) to
                context.getString(com.test.application.core.R.string.tag_eng_suntan),
        context.getString(com.test.application.core.R.string.tag_mask) to
                context.getString(com.test.application.core.R.string.tag_eng_mask)
    )

    fun setupTags(tags: Array<String>) {
        chipGroup.removeAllViews()
        val allTagText = context.getString(com.test.application.core.R.string.tag_all)

        tags.forEach { tag ->
            val chip = createChip(tag, tag == allTagText)
            setupChipListener(chip, tagMapping[tag] ?: tag)
            chipGroup.addView(chip)
        }
    }

    private fun createChip(tag: String, isSelected: Boolean): Chip {
        return (LayoutInflater.from(context)
            .inflate(R.layout.custom_chip, chipGroup, false) as Chip).apply {
            text = tag
            isCheckable = true
            isCheckedIconVisible = false
            isChecked = isSelected
            isCloseIconVisible = isSelected
        }
    }

    private fun setupChipListener(chip: Chip, tagId: String) {
        chip.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                onTagSelected(tagId)
                updateCloseIconVisibility(chip)
            }
        }
    }

    private fun updateCloseIconVisibility(selectedChip: Chip) {
        chipGroup.children.forEach { child ->
            (child as? Chip)?.let {
                if (it != selectedChip) it.isCloseIconVisible = false
            }
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