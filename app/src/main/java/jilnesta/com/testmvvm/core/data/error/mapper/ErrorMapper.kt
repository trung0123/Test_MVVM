package jilnesta.com.testmvvm.core.data.error.mapper

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import jilnesta.com.testmvvm.R
import jilnesta.com.testmvvm.core.data.error.NETWORK_ERROR
import jilnesta.com.testmvvm.core.data.error.NO_INTERNET_CONNECTION
import jilnesta.com.testmvvm.core.data.error.PASS_WORD_ERROR_EMPTY
import jilnesta.com.testmvvm.core.data.error.USER_NAME_ERROR
import javax.inject.Inject

class ErrorMapper @Inject constructor(@ApplicationContext val context: Context) :
    ErrorMapperSource {

    override fun getErrorString(errorId: Int): String {
        return context.getString(errorId)
    }

    override val errorsMap: Map<String, String>
        get() = mapOf(
            Pair(NO_INTERNET_CONNECTION, getErrorString(R.string.no_internet)),
            Pair(NETWORK_ERROR, getErrorString(R.string.network_error)),
            Pair(PASS_WORD_ERROR_EMPTY, getErrorString(R.string.invalid_password)),
            Pair(USER_NAME_ERROR, getErrorString(R.string.invalid_username)),
        ).withDefault { getErrorString(R.string.network_error) }
}