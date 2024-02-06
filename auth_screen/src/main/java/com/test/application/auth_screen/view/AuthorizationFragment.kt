package com.test.application.auth_screen.view

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.test.application.auth_screen.databinding.FragmentAuthorizationBinding
import com.test.application.auth_screen.utils.BUNDLE_KEY_NAME
import com.test.application.auth_screen.utils.BUNDLE_KEY_PHONE_NUMBER
import com.test.application.auth_screen.utils.BUNDLE_KEY_SECOND_NAME
import com.test.application.auth_screen.utils.PhoneNumberValidator
import com.test.application.core.R
import com.test.application.core.navigation.AuthNavigationListener
import com.test.application.core.view.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthorizationFragment : BaseFragment<FragmentAuthorizationBinding>(
    FragmentAuthorizationBinding::inflate
) {

    private val viewModel: AuthorizationViewModel by viewModels()
    private var navigationHandler: AuthNavigationListener? = null
    private val phoneNumberValidator = PhoneNumberValidator()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is AuthNavigationListener) {
            navigationHandler = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        navigationHandler = null
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(BUNDLE_KEY_NAME, binding.etName.text.toString())
        outState.putString(BUNDLE_KEY_SECOND_NAME, binding.etSecondName.text.toString())
        outState.putString(BUNDLE_KEY_PHONE_NUMBER, binding.etPhoneNumber.text.toString())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        restoreData(savedInstanceState)
        disableHints()
        setNameEditText()
        setPhoneEditText()
        setLoginButton()
    }

    private fun disableHints() {
        with(binding){
            etPhoneNumber.hint = null
            etName.hint = null
            etSecondName.hint = null
        }
    }

    private fun restoreData(savedInstanceState: Bundle?) {
        savedInstanceState?.let {
            val name = it.getString(BUNDLE_KEY_NAME, "")
            val secondName = it.getString(BUNDLE_KEY_SECOND_NAME, "")
            val phoneNumber = it.getString(BUNDLE_KEY_PHONE_NUMBER, "")

            binding.etName.setText(name)
            binding.etSecondName.setText(secondName)
            binding.etPhoneNumber.setText(phoneNumber)
        }
    }

    private fun setLoginButton() {
        binding.btnLogin.setOnClickListener {
            saveAuthData()
            navigationHandler?.onAuthSuccess()
        }
    }

    private fun setPhoneEditText() {
        binding.etPhoneNumber.addTextChangedListener(object : TextWatcher {
            private var isFormatting = false
            private var lastFormattedText: String? = null

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                if (isFormatting) return

                isFormatting = true

                val digits = p0.toString().filter { it.isDigit() }
                if(digits.length > 16) {
                    val truncatedDigits = digits.substring(0, 16)
                    val formatted = phoneNumberValidator.format(truncatedDigits)
                    binding.etPhoneNumber.setText(formatted)
                    binding.etPhoneNumber.setSelection(formatted.length)
                } else {
                    val formatted = phoneNumberValidator.format(digits)
                    if (p0.toString() != formatted && formatted != lastFormattedText) {
                        lastFormattedText = formatted
                        binding.etPhoneNumber.setText(formatted)
                        binding.etPhoneNumber.setSelection(
                            formatted.length.coerceAtMost(binding.etPhoneNumber.text?.length ?: 0)
                        )
                    }
                }
                isFormatting = false
                updateLoginButtonState()
            }

            override fun afterTextChanged(p0: Editable?) {}
        })
    }

    private fun setNameEditText() {
        val editTexts = listOf(Pair(binding.etName, binding.etLayoutName),
            Pair(binding.etSecondName, binding.etLayoutSecondName))
        editTexts.forEach { (editText, textInputLayout) ->
            editText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    val valid = p0.toString().all { it in 'А'..'я' }
                    if(valid) {
                        textInputLayout.boxBackgroundColor =
                            ContextCompat.getColor(requireContext(), R.color.background_light_grey)
                    } else {
                        textInputLayout.boxBackgroundColor =
                            ContextCompat.getColor(requireContext(), R.color.text_light_pink)
                    }
                    updateLoginButtonState()
                }

                override fun afterTextChanged(p0: Editable?) {}
            })
        }
    }

    private fun updateLoginButtonState() {
        val name = binding.etName.text.toString()
        val secondName = binding.etSecondName.text.toString()
        val phoneNumber = binding.etPhoneNumber.text.toString()

        val nameValid = name.isNotEmpty() && name.all { it in 'А'..'я' }
        val secondNameValid = secondName.isNotEmpty() && secondName.all { it in 'А'..'я' }
        val phoneValid = phoneNumber.isNotEmpty() && phoneNumberValidator.isValid(phoneNumber)

        binding.btnLogin.isEnabled = nameValid && secondNameValid && phoneValid
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