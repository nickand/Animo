package com.nosti.animo.ui

import android.app.Activity
import androidx.fragment.app.Fragment

interface OnSetTitleAndNavigateListener {

    fun setTitleToolbar(title: String)
    fun navigateTo(fragment: Fragment)
    fun navigateTo(fragment: Fragment, addToBackStack: Boolean)
    fun openWebView(activity: Activity, url: String)
}