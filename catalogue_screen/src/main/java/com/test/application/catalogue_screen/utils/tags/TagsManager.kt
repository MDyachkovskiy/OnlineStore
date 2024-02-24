package com.test.application.catalogue_screen.utils.tags

import android.content.Context
import android.view.LayoutInflater
import androidx.core.view.children
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.test.application.catalogue_screen.R
import com.test.application.core.domain.product.Product

class TagsManager(
    private var context: Context?,
    private val chipGroup: ChipGroup,
    private val onTagSelected: (String?) -> Unit
) {

    private val tagMapping = mapOf(
        context?.getString(com.test.application.core.R.string.tag_face) to
                context?.getString(com.test.application.core.R.string.tag_eng_face),
        context?.getString(com.test.application.core.R.string.tag_body) to
                context?.getString(com.test.application.core.R.string.tag_eng_body),
        context?.getString(com.test.application.core.R.string.tag_suntan) to
                context?.getString(com.test.application.core.R.string.tag_eng_suntan),
        context?.getString(com.test.application.core.R.string.tag_mask) to
                context?.getString(com.test.application.core.R.string.tag_eng_mask)
    )

    private var selectedChipId: Int? = null

    fun setupTags(tags: Array<String>) {
        chipGroup.removeAllViews()

        tags.forEach { tag ->
            val chip = createChip(tag)
            setupChipListener(chip, tagMapping[tag] ?: tag)
            chipGroup.addView(chip)
        }
        selectAllTagIfNoneSelected()
    }

    private fun createChip(tag: String): Chip {
        return (LayoutInflater.from(context)
            .inflate(R.layout.custom_chip, chipGroup, false) as Chip).apply {
            text = tag
            isCheckable = true
            isCheckedIconVisible = false
            isCloseIconVisible = false

            setOnCloseIconClickListener {
                isSelected = false
                selectedChipId = null
                onTagSelected(null)
                selectAllTag()
                updateCloseIconVisibility()
            }
        }
    }

    private fun setupChipListener(chip: Chip, tagId: String) {
        chip.setOnClickListener {
            if (selectedChipId == chip.id) {
                selectedChipId = null
                chip.isChecked = false
                onTagSelected(null)
                selectAllTag()
            } else {
                selectedChipId = chip.id
                onTagSelected(tagId)
            }
            updateCloseIconVisibility()
        }
    }

    private fun updateCloseIconVisibility() {
        chipGroup.children.forEach { child ->
            (child as? Chip)?.let { chip ->
                chip.isCloseIconVisible = chip.id == selectedChipId
            }
        }
    }

    private fun selectAllTag() {
        chipGroup.children.forEach { child ->
            (child as? Chip)?.let {
                if (it.text == context?.getString(com.test.application.core.R.string.tag_all)) {
                    it.isChecked = true
                    selectedChipId = it.id
                    onTagSelected(tagMapping[context?.getString(com.test.application.core.R.string.tag_all)])
                    updateCloseIconVisibility()
                }
            }
        }
    }

    private fun selectAllTagIfNoneSelected() {
        if (selectedChipId == null) {
            selectAllTag()
        }
    }

    fun filterProductsByTag(products: List<Product>, selectedTag: String?): List<Product> {
        return if (selectedTag.isNullOrEmpty() ||
            selectedTag == context?.getString(com.test.application.core.R.string.tag_all)
        ) {
            products
        } else {
            products.filter { it.tags.contains(selectedTag) }
        }
    }

    fun cleanup() {
        context = null
        chipGroup.removeAllViews()
    }
}