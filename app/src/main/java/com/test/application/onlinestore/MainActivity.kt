package com.test.application.onlinestore

import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.AppCompatEditText
import com.test.application.auth_screen.AuthorizationFragment
import com.test.application.core.navigation.AuthNavigationListener

class MainActivity : AppCompatActivity(), AuthNavigationListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (!isUserLoggedIn()) {
            showAuthFragment()
        }
    }

    private fun isUserLoggedIn(): Boolean {
        return false
    }

    private fun showAuthFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.auth_container, AuthorizationFragment())
        transaction.commit()
    }

    fun onUserLoggedIn() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.remove(AuthorizationFragment())
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

    override fun onAuthSuccess() {
        TODO("Not yet implemented")
    }
}