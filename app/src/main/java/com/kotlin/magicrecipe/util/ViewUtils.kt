package com.kotlin.magicrecipe.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import com.kotlin.magicrecipe.ui.HomeActivity

fun Context.toast(message: String){
    Toast.makeText(this, message, Toast.LENGTH_LONG ).show()
}

object AppConstant {
    const val DEFAULT_TIMEOUT: Long = 1000
    const val URL: String = "http://www.recipepuppy.com/"
}
fun ProgressBar.show(){
    visibility = View.VISIBLE
}

fun ProgressBar.hide(){
    visibility = View.GONE
}



