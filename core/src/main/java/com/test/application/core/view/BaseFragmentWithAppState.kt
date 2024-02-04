package com.test.application.core.view

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.test.application.core.R
import com.test.application.core.databinding.LayoutLoadingBinding
import com.test.application.core.utils.AppState

typealias Inflate<F> = (LayoutInflater, ViewGroup?, Boolean) -> F

@Suppress("UNCHECKED_CAST")
abstract class BaseFragmentWithAppState<T : AppState, I, VB : ViewBinding>(
    private val inflate: Inflate<VB>
) : Fragment() {

    private var _bindingLoading: LayoutLoadingBinding? = null
    private val bindingLoading get() = _bindingLoading!!

    private var _binding: VB? = null
    val binding: VB get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflate.invoke(inflater, container, false)
        _bindingLoading = LayoutLoadingBinding.inflate(layoutInflater)

        setupLoadingLayout()

        return binding.root
    }

    private fun setupLoadingLayout() {
        val rootLayout = (binding.root as? ViewGroup)

        val layoutParams = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT,
            FrameLayout.LayoutParams.MATCH_PARENT
        )
        _bindingLoading?.root?.layoutParams = layoutParams
        _bindingLoading?.root?.setBackgroundColor(
            ContextCompat.getColor(requireContext(), R.color.white)
        )

        rootLayout?.addView(_bindingLoading?.root)
    }

    override fun onDestroyView() {
        _binding = null
        _bindingLoading = null
        super.onDestroyView()
    }

    protected fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success<*> -> {
                showWorkingView()
                val data = appState.data as? I
                if (data != null) setupData(data)
            }

            is AppState.Loading -> {
                showViewLoading()
            }

            is AppState.Error -> {
                showWorkingView()
                showErrorDialog(appState.error.message)
            }
        }
    }

    abstract fun setupData(data: I)

    private fun showViewLoading() {
        bindingLoading.loadingLayout.animate().cancel()
        bindingLoading.loadingLayout.apply {
            alpha = 1.0f
            visibility = View.VISIBLE
        }
    }

   private fun showErrorDialog(message: String?) {
       Toast.makeText(requireContext(),
           message ?: getString(R.string.error_message), Toast.LENGTH_SHORT).show()
   }

    private fun showWorkingView() {
        bindingLoading.loadingLayout.animate()
            .alpha(0.0f)
            .setDuration(300)
            .setListener(object: AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    if (_bindingLoading != null) {
                        bindingLoading.loadingLayout.visibility = View.GONE
                    }
                }
            })
    }
}