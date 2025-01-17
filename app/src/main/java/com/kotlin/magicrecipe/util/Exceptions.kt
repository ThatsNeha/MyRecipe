package com.kotlin.magicrecipe.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.kotlin.magicrecipe.ui.HomeActivity
import java.io.IOException

class ApiException(message: String) : IOException(message)
fun isInternetAvailable(context: HomeActivity): Boolean {
    var result = false
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
    connectivityManager?.let {
        it.getNetworkCapabilities(connectivityManager.activeNetwork)?.apply {
            result = when {
                hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }
        }
    }
    return result
}