package jilnesta.com.testmvvm.core.data.local

import android.content.Context
import android.content.SharedPreferences
import jilnesta.com.testmvvm.utils.FAVOURITES_KEY
import jilnesta.com.testmvvm.utils.SHARED_PREFERENCES_FILE_NAME
import jilnesta.com.testmvvm.utils.USER_CODE
import jilnesta.com.testmvvm.utils.USER_TOKEN
import jilnesta.com.testmvvm.core.data.dto.Resource
import javax.inject.Inject

class LocalData @Inject constructor(val context: Context) {

    fun setUserToken(value: String): Resource<Boolean> {
        val isSuccess = BasePreferences(context).putString(USER_TOKEN, value)
        return Resource.Success(isSuccess)
    }

    fun getUserToken(): Resource<String> {
        val token = BasePreferences(context).getString(USER_TOKEN, "")
        return Resource.Success(token)
    }

    fun setUserCode(value: String): Resource<Boolean> {
        val isSuccess = BasePreferences(context).putString(USER_CODE, value)
        return Resource.Success(isSuccess)
    }

    fun getUserCode(): Resource<String> {
        val code = BasePreferences(context).getString(USER_CODE, "")
        return Resource.Success(code)
    }
}