package com.test.application.favorite_screen.view

import com.test.application.core.view.BaseFragment
import com.test.application.favorite_screen.databinding.FragmentBrandListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BrandsFragment : BaseFragment<FragmentBrandListBinding>(
    FragmentBrandListBinding::inflate
) {
}