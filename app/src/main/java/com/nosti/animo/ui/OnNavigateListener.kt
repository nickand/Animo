package com.nosti.animo.ui

import android.app.Activity
import androidx.fragment.app.Fragment

interface OnNavigateListener {

    fun openWebView(activity: Activity, url: String)
}