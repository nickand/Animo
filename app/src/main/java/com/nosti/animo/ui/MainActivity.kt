package com.nosti.animo.ui

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.nosti.animo.R
import com.nosti.animo.ui.main.AnimoFragment
import com.thefinestartist.finestwebview.FinestWebView
import kotlinx.android.synthetic.main.activity_main.*
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper


class MainActivity : AppCompatActivity(), OnSetTitleAndNavigateListener {

    private var mFragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViews()
    }

    private fun initViews() {
        setContentView(R.layout.activity_main)

        setTitleToolbar(getString(R.string.app_name))
        setSupportActionBar(containerToolbar)

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.action_dashboard -> {
                    findNavController(findViewById(R.id.navHostFragment))
                }
                R.id.action_favorite -> {}
                R.id.action_category -> {}
            }
            true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == R.id.action_settings) {
            containerToolbar.visibility = View.GONE
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun setTitleToolbar(title: String) {
        toolbarTitle.text = title
    }

    override fun openWebView(activity: Activity, url: String) {
        FinestWebView.Builder(activity).show(url)
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }

    override fun onBackPressed() {
        setTitleToolbar(getString(R.string.app_name))
        super.onBackPressed()
    }

    companion object {
        private val CLASS_TAG = MainActivity::class.java.simpleName
    }
}