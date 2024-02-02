package com.test.application.auth_screen

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.viewModels
import com.test.application.auth_screen.databinding.FragmentAuthorizationBinding
import com.test.application.core.view.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthorizationFragment : BaseFragment<FragmentAuthorizationBinding>(
    FragmentAuthorizationBinding::inflate
) {

    private val viewModel: AuthorizationViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setNameEditText()
        setPhoneEditText()
    }

    private fun setPhoneEditText() {
        binding.etPhoneNumber.addTextChangedListener(object : TextWatcher{
            private var isFormatting = false
            private var lastFormattedText: String? = null

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (isFormatting) return

                isFormatting = true

                val digits = p0.toString().filter { it.isDigit() }
                val formatted = buildString {
                    append("+7 ")
                    digits.drop(1).forEachIndexed { index, c ->
                        if (index == 3 || index == 6) append(" ")
                        if (index == 8 || index == 10) append(" ")
                        append(c)
                    }
                }

                if (p0.toString() != formatted && formatted != lastFormattedText) {
                    lastFormattedText = formatted
                    binding.etPhoneNumber.setText(formatted)
                    binding.etPhoneNumber.setSelection(formatted.length
                            .coerceAtMost(binding.etPhoneNumber.text?.length ?: 0))
                }
                isFormatting = false
                updateLoginButtonState()
            }
            override fun afterTextChanged(p0: Editable?) {}
        })
    }

    private fun setNameEditText() {
        val editTexts = listOf(binding.etName, binding.etSecondName)
        editTexts.forEach{ editText ->
            editText.addTextChangedListener(object: TextWatcher{
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    val valid = p0.toString().all { it in 'А'..'я' }
                    editText.background = if (valid) null else ColorDrawable(Color.RED)
                    updateLoginButtonState()
                }

                override fun afterTextChanged(p0: Editable?) {}
            })
        }
    }

    private fun updateLoginButtonState() {
        val nameValid = binding.etName.text.toString().all { it in 'А'..'я' }
        val secondNameValid = binding.etSecondName.text.toString().all { it in 'А'..'я' }
        val phoneValid = (binding.etPhoneNumber.text?.length ?: 0) >= 15
        binding.btnLogin.isEnabled = nameValid && secondNameValid && phoneValid
        saveAuthData()
    }

    private fun saveAuthData() {
        if (binding.btnLogin.isEnabled) {
            val name = binding.etName.text.toString()
            val secondName = binding.etSecondName.text.toString()
            val phoneNumber = binding.etPhoneNumber.text.toString()
            viewModel.saveAuthData(name, secondName, phoneNumber)
        }
    }
}