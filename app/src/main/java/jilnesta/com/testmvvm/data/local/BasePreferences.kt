package jilnesta.com.testmvvm.data.local

import android.content.Context
import android.content.SharedPreferences
import jilnesta.com.testmvvm.SHARED_PREFERENCES_FILE_NAME

class BasePreferences(val context: Context) {

    private var sharePref: SharedPreferences =
        context.getSharedPreferences(SHARED_PREFERENCES_FILE_NAME, Context.MODE_PRIVATE)

    fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return sharePref.getBoolean(key, defaultValue)
    }

    fun putBoolean(key: String, value: Boolean) {
        sharePref.edit().putBoolean(key, value).apply()
    }

    fun getString(key: String, defaultValue: String): String {
        return sharePref.getString(key, defaultValue) ?: ""
    }

    fun putString(key: String, value: String): Boolean {
        val editor = sharePref.edit()
        editor.putString(key, value).apply()
        return editor.commit()
    }

    fun getInt(key: String, defaultValue: Int): Int {
        return sharePref.getInt(key, defaultValue)
    }

    fun putInt(key: String, value: Int) : Boolean {
        val editor = sharePref.edit()
        editor.putInt(key, value)
        return editor.commit()
    }

    fun getLong(key: String, defaultValue: Long): Long {
        return sharePref.getLong(key, defaultValue)
    }

    fun putLong(key: String, value: Long) : Boolean {
        val editor = sharePref.edit()
        editor.putLong(key, value)
        return editor.commit()
    }

    fun getFloat(key: String, defaultValue: Float): Float {
        return sharePref.getFloat(key, defaultValue)
    }

    fun putFloat(key: String, value: Float) : Boolean {
        val editor = sharePref.edit()
        editor.putFloat(key, value)
        return editor.commit()
    }
}