package jilnesta.com.testmvvm.core.data.error

/**
 * Created by AhmedEltaher
 */

class Error(val code: String, val description: String) {
    constructor(exception: Exception) : this(code = DEFAULT_ERROR, description = exception.message
            ?: "")
}

const val NO_INTERNET_CONNECTION = "NO_INTERNET_CONNECTION"
const val NETWORK_ERROR = "NETWORK_ERROR"
const val DEFAULT_ERROR = "DEFAULT_ERROR"

// Login
const val PASS_WORD_ERROR_EMPTY = "PASS_WORD_ERROR_EMPTY"
const val USER_NAME_ERROR = "USER_NAME_ERROR"
const val PASS_WORD_ERROR_LENGTH = "PASS_WORD_ERROR_LENGTH"
const val DUPLICATE_EMAIL = "DUPLICATE_EMAIL"
const val LOGIN_FAIL = "LOGIN_FAIL"
