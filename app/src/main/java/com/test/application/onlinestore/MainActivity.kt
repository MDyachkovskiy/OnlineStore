package com.test.application.onlinestore

import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.appcompat.widget.AppCompatEditText
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.test.application.auth_screen.AuthorizationFragment
import com.test.application.onlinestore.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
            .also{ setContentView(it.root) }
        setupNavHost()
        setupBottomNavMenu(navController)
        observeUserLoggedInStatus()
    }

    private fun setupBottomNavMenu(navController: NavController) {
        val bottomNav = findViewById<BottomNavigationView>(R.id.navigation_menu)
        bottomNav?.setupWithNavController(navController)
    }

    private fun setupNavHost() {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.main_container) as NavHostFragment
        navController = navHostFragment.navController
    }

    private fun observeUserLoggedInStatus() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.isUserLoggedIn().collect { isLoggedIn ->
                    if (isLoggedIn) {
                        showMainContent()
                    } else {
                        showAuthFragment()
                    }
                }
            }
        }
    }

    private fun showMainContent() {
        val mainContainer = binding.mainContainer
        val authContainer = binding.authContainer
        mainContainer.visibility = View.VISIBLE
        authContainer.visibility = View.GONE
        mainContainer.isFocusable = true
        authContainer.isFocusable = false
    }

    private fun showAuthFragment() {
        val mainContainer = binding.mainContainer
        val authContainer = binding.authContainer
        mainContainer.visibility = View.GONE
        authContainer.visibility = View.VISIBLE
        mainContainer.isFocusable = false
        authContainer.isFocusable = true
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.auth_container, AuthorizationFragment())
        transaction.commit()
    }

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            val v = currentFocus
            if (v is AppCompatEditText) {
                val outRect = Rect()
                v.getGlobalVisibleRect(outRect)
                if (!outRect.contains(event.rawX.toInt(), event.rawY.toInt())) {
                    v.clearFocus()
                    val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0)
                }
            }
        }
        return super.dispatchTouchEvent(event)
    }
}