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

class CategoriesActivity : AppCompatActivity(), OnSetTitleAndNavigateListener {

    private var mFragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViews()

        navigateToCategoriesFragment()
    }

    private fun initViews() {

        setTitleToolbar(getString(R.string.category))
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

    override fun setTitleToolbar(title: String) {
        toolbarTitle.setText(title)
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

            fragmentTransaction.setCustomAnimations(
                    R.anim.enter_from_right, R.anim.exit_to_left,
                    R.anim.enter_from_left, R.anim.exit_to_right)
            fragmentTransaction.replace(R.id.fragment_container, fragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()


        }

        mFragment = fragment
    }

    override fun openWebView(activity: Activity, url: String) {
        FinestWebView.Builder(activity).show(url)
    }

    companion object {

        private val CLASS_TAG = CategoriesActivity::class.java.simpleName
    }
}
