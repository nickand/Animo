package com.nosti.animo.utils

import android.content.Context
import android.net.ConnectivityManager

class Utils {

    companion object {
        fun isNetworkConnected(context: Context?): Boolean {
            context.let {
                val cm = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                return cm.activeNetworkInfo != null
            }
        }
    }
}