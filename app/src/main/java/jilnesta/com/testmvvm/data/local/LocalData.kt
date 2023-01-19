package jilnesta.com.testmvvm.data.local

import android.content.Context
import android.content.SharedPreferences
import jilnesta.com.testmvvm.FAVOURITES_KEY
import jilnesta.com.testmvvm.SHARED_PREFERENCES_FILE_NAME
import jilnesta.com.testmvvm.data.Resource
import jilnesta.com.testmvvm.data.dto.login.LoginRequest
import jilnesta.com.testmvvm.data.dto.login.LoginResponse
import jilnesta.com.testmvvm.data.error.PASS_WORD_ERROR
import javax.inject.Inject

class LocalData @Inject constructor(val context: Context) {

    fun doLogin(loginRequest: LoginRequest): Resource<LoginResponse> {
        if (loginRequest == LoginRequest("ahmed@ahmed.ahmed", "ahmed")) {
            return Resource.Success(
                LoginResponse(
                    "123", "Ahmed", "Mahmoud",
                    "FrunkfurterAlle", "77", "12000", "Berlin",
                    "Germany", "ahmed@ahmed.ahmed"
                )
            )
        }
        return Resource.DataError(PASS_WORD_ERROR)
    }

    fun getCachedFavorites(): Resource<Set<String>> {
        val sharePref = context.getSharedPreferences(SHARED_PREFERENCES_FILE_NAME, 0)
        return Resource.Success(sharePref.getStringSet(FAVOURITES_KEY, setOf()) ?: setOf())
    }

    fun isFavorite(id: String): Resource<Boolean> {
        val sharePref = context.getSharedPreferences(SHARED_PREFERENCES_FILE_NAME, 0)
        val cache = sharePref.getStringSet(FAVOURITES_KEY, setOf<String>()) ?: setOf()
        return Resource.Success(cache.contains(id))
    }

    fun cacheFavorites(ids: Set<String>): Resource<Boolean> {
        val sharePref = context.getSharedPreferences(SHARED_PREFERENCES_FILE_NAME, 0)
        val editor: SharedPreferences.Editor = sharePref.edit()
        editor.putStringSet(FAVOURITES_KEY, ids)
        editor.apply()
        val isSuccess = editor.commit()
        return Resource.Success(isSuccess)
    }

    fun removeFromFavorites(id: String): Resource<Boolean> {
        val sharePref = context.getSharedPreferences(SHARED_PREFERENCES_FILE_NAME, 0)
        val set = sharePref.getStringSet(FAVOURITES_KEY, mutableSetOf<String>())?.toMutableSet()
            ?: mutableSetOf()
        if (set.contains(id)) {
            set.remove(id)
        }
        val editor: SharedPreferences.Editor = sharePref.edit()
        editor.clear()
        editor.apply()
        editor.putStringSet(FAVOURITES_KEY, set)
        editor.apply()
        val isSuccess = editor.commit()
        return Resource.Success(isSuccess)
    }

}