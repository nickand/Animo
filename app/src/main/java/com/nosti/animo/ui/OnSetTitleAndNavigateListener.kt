package com.nosti.animo.ui

import android.app.Activity
import androidx.fragment.app.Fragment

interface OnSetTitleAndNavigateListener {

    fun setTitleToolbar(title: String)
    fun openWebView(activity: Activity, url: String)
}