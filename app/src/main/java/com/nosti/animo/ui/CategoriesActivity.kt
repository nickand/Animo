package com.nosti.animo.ui

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.nosti.animo.R
import com.thefinestartist.finestwebview.FinestWebView
import kotlinx.android.synthetic.main.activity_categories.*
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper

class CategoriesActivity : AppCompatActivity(), OnNavigateListener {

    private var mFragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViews()

        navigateToCategoriesFragment()
    }

    private fun initViews() {
        setSupportActionBar(containerToolbar)
    }

    private fun navigateToCategoriesFragment() {
        /*var fragment: Fragment? = null
        fragment = CategoriesFragment.newInstance()
        navigateTo((fragment as BaseFragment?)!!)*/
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }

    override fun openWebView(activity: Activity, url: String) {
        FinestWebView.Builder(activity).show(url)
    }

    companion object {

        private val CLASS_TAG = CategoriesActivity::class.java.simpleName
    }
}
