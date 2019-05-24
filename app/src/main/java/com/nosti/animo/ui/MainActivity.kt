package com.nosti.animo.ui

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
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

        navigateToAnimoFragment()
    }

    private fun navigateToAnimoFragment() {
        val fragment: Fragment?
        fragment = AnimoFragment.newInstance()
        navigateTo(fragment)
    }

    private fun initViews() {
        setContentView(R.layout.activity_main)

        setTitleToolbar(getString(R.string.app_name))
        setSupportActionBar(containerToolbar)

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.action_dashboard -> {navigateToAnimoFragment()}
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

    override fun navigateTo(fragment: Fragment) {
        navigateTo(fragment, true)
    }

    override fun navigateTo(fragment: Fragment, addToBackStack: Boolean) {
        val manager = supportFragmentManager

        if (!addToBackStack) {
            manager.popBackStackImmediate()
        }

        val fragmentTransaction = manager.beginTransaction()

        if (mFragment == null) {
            fragmentTransaction.add(R.id.fragment_container, fragment).commit()

        } else {

            /*fragmentTransaction.setCustomAnimations(
                R.anim.enter_from_right, R.anim.exit_to_left,
                R.anim.enter_from_left, R.anim.exit_to_right
            )*/
            fragmentTransaction.replace(R.id.fragment_container, fragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()


        }

        mFragment = fragment
    }

    override fun openWebView(activity: Activity, url: String) {
        FinestWebView.Builder(activity).show(url)
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }

    override fun onBackPressed() {
        containerToolbar.visibility = View.VISIBLE
        setTitleToolbar(getString(R.string.app_name))
        navigateToAnimoFragment()
    }

    companion object {
        private val CLASS_TAG = MainActivity::class.java.simpleName
    }
}