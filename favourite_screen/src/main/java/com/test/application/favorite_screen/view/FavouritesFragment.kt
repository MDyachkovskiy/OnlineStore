package com.test.application.favorite_screen.view

import android.content.Context
import android.os.Bundle
import android.view.View
import com.google.android.material.tabs.TabLayoutMediator
import com.test.application.core.navigation.AccountProfileNavigation
import com.test.application.core.view.BaseFragment
import com.test.application.favorite_screen.adapter.ViewPagerAdapter
import com.test.application.favorite_screen.databinding.FragmentFavouritesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouritesFragment : BaseFragment<FragmentFavouritesBinding>(
    FragmentFavouritesBinding::inflate
) {

    private var navigationHandler: AccountProfileNavigation? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is AccountProfileNavigation) {
            navigationHandler = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        navigationHandler = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewPagerWithTabs()
    }

    private fun setupViewPagerWithTabs() {
        val viewPager = binding.fragmentViewPager
        viewPager.adapter = ViewPagerAdapter(this)

        val tabLayout = binding.tabs
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> getString(com.test.application.core.R.string.tab_items)
                1 -> getString(com.test.application.core.R.string.tab_brands)
                else -> null
            }
        }.attach()
    }
}