package com.test.application.account_profile_screen.view

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.test.application.account_profile_screen.R
import com.test.application.account_profile_screen.databinding.FragmentAccountProfileBinding
import com.test.application.account_profile_screen.utils.getFavouritesCountString
import com.test.application.core.domain.auth.UserLogin
import com.test.application.core.navigation.AccountProfileNavigation
import com.test.application.core.utils.AppState
import com.test.application.core.view.BaseFragmentWithAppState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AccountProfileFragment : BaseFragmentWithAppState<AppState, UserLogin, FragmentAccountProfileBinding>(
    FragmentAccountProfileBinding::inflate
) {
    private val viewModel: AccountProfileViewModel by viewModels()
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
        initViewModel()
        setupButtons()
    }

    private fun initViewModel() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.stateFlow.collect {appState ->
                    renderData(appState)
                }
            }
        }
        requestData()
    }

    private fun requestData() {
        viewModel.getUserInfo()
    }

    override fun setupData(data: UserLogin) {
        with(binding) {
            tvUserName.text = getString(R.string.user_name, data.name, data.secondName)
            tvUserPhone.text = data.phoneNumber
            setFavouriteString(data)

        }
    }

    private fun setFavouriteString(data: UserLogin) {
        with(binding.menuLayout){
            if (data.favoriteCount > 0) {
                tvFavoriteCount.visibility = View.VISIBLE
                tvFavoriteCount.text = getFavouritesCountString(requireContext(), data.favoriteCount)
                restoreOriginalConstraints()
            } else {
                tvFavoriteCount.visibility = View.GONE
                updateConstraintsForNoFavorites()
            }
        }
    }

    private fun updateConstraintsForNoFavorites() {
        val constraintSet = ConstraintSet().apply {
            clone(binding.menuLayout.favoriteBlockConstraints)
            connect(R.id.favorite_label, ConstraintSet.BOTTOM, R.id.favorite_icon, ConstraintSet.BOTTOM, 0)
        }
        constraintSet.applyTo(binding.menuLayout.favoriteBlockConstraints)
    }

    private fun restoreOriginalConstraints() {
        val constraintSet = ConstraintSet().apply {
            clone(binding.menuLayout.favoriteBlockConstraints)
            connect(R.id.favorite_label, ConstraintSet.BOTTOM, ConstraintSet.UNSET, ConstraintSet.BOTTOM, 0)
            connect(R.id.favorite_label, ConstraintSet.TOP, R.id.favorite_icon, ConstraintSet.TOP, 0)
        }
        constraintSet.applyTo(binding.menuLayout.favoriteBlockConstraints)
    }

    private fun setupButtons() {
        binding.btnLogoutSecond.setOnClickListener {
            viewModel.logoutUser()
            navigationHandler?.navigateToRegistrationScreen()
        }
        binding.menuLayout.favoriteBlock.setOnClickListener {
            navigationHandler?.navigateToFavouriteScreen()
        }
    }
}

