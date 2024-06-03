package jilnesta.com.testmvvm.core.data.local

import android.content.Context
import jilnesta.com.testmvvm.core.data.dto.Resource
import javax.inject.Inject

const val USER_TOKEN = "user_token"
const val USER_CODE = "user_code"

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