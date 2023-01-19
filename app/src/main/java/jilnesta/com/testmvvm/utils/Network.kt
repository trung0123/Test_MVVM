package jilnesta.com.testmvvm.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import javax.inject.Inject

class Network @Inject constructor(val context: Context) : NetworkConnectivity {
    override fun getNetworkInfo(): NetworkInfo? {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo
    }

    override fun isConnected(): Boolean {
        TODO("Not yet implemented")
    }

}

interface NetworkConnectivity {
    fun getNetworkInfo(): NetworkInfo?
    fun isConnected(): Boolean
}