package com.test.application.auth_screen.utils

class PhoneNumberValidator {
    fun isValid(phoneNumber: String): Boolean {
        return phoneNumber.startsWith("+7") && phoneNumber.length == 16
    }

    fun format(phoneNumber: String): String {
        var formattedNumber = phoneNumber.replace(Regex("[^\\d]"), "")
        if (formattedNumber.length > 11) {
            formattedNumber = formattedNumber.substring(0, 11)
        }

        val builder = StringBuilder("+7 ")
        for (i in 1 until formattedNumber.length) {
            if (i == 4 || i == 7 || i == 9) {
                builder.append(" ")
            }
            builder.append(formattedNumber[i])
        }

        return builder.toString()
    }
}
