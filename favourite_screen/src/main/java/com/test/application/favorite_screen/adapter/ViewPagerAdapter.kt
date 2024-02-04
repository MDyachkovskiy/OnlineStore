package com.test.application.favorite_screen.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.test.application.favorite_screen.view.BrandsFragment
import com.test.application.favorite_screen.view.FavouritesListFragment

class ViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val fragments = arrayOf(FavouritesListFragment(), BrandsFragment())

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
}