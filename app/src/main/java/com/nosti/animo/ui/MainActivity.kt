package com.nosti.animo.ui

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import com.nosti.animo.R
import com.nosti.animo.ui.favorite.FavoriteFragment
import com.nosti.animo.ui.main.AnimoFragment
import com.nosti.animo.ui.navigation.Navigation
import com.nosti.animo.ui.search.SearchFragment
import com.thefinestartist.finestwebview.FinestWebView
import kotlinx.android.synthetic.main.activity_main.*
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper


class MainActivity : AppCompatActivity(), OnNavigateListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
    }

    private fun initViews() {
        initHomeFragment()

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.action_dashboard -> {
                    if (supportFragmentManager.fragments.isEmpty()) {
                        initHomeFragment()
                    } else {
                        Navigation.replaceFragment(supportFragmentManager,
                            R.id.fragmentContainer, AnimoFragment())
                    }
                }
                R.id.action_search -> {
                    initSearchFragment()
                }
                R.id.action_favorite -> {
                    initFavoriteFragment()
                }
            }
            true
        }
    }

    private fun initSearchFragment() {
        Navigation.addFragment(supportFragmentManager, R.id.fragmentContainer, SearchFragment())
    }

    private fun initFavoriteFragment() {
        Navigation.addFragment(supportFragmentManager, R.id.fragmentContainer, FavoriteFragment())
    }

    private fun initHomeFragment() {
        Navigation.addFragment(supportFragmentManager, R.id.fragmentContainer, AnimoFragment())
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun openWebView(activity: Activity, url: String) {
        FinestWebView.Builder(activity).show(url)
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 1) {
            finish()
        } else {
            super.onBackPressed()
        }
    }

    companion object {
        private val CLASS_TAG = MainActivity::class.java.simpleName
    }
}