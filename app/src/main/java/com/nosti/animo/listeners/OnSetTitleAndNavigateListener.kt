package com.nosti.animo.listeners

import android.app.Activity
import com.nosti.animo.fragments.BaseFragment

interface OnSetTitleAndNavigateListener {

    fun setTitleToolbar(title: String)
    fun navigateTo(fragment: BaseFragment)
    fun navigateTo(fragment: BaseFragment, addToBackStack: Boolean)
    fun openWebView(activity: Activity, url: String)
}