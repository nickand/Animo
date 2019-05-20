package com.nosti.animo.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nosti.animo.R
import com.nosti.animo.adapters.AnimoAdapter
import com.nosti.animo.extensions.inflate
import com.nosti.animo.models.AnimoDb
import kotlinx.android.synthetic.main.fragment_animo.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AnimoFragment : BaseFragment() {

    private var mFragment: Fragment? = null

    private val adapter = AnimoAdapter {
        navigateTo(DetailFragment.newInstanceWithArguments(it), false)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.fragment_animo)
    }

    @SuppressLint("WrongConstant")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mLayoutManager = GridLayoutManager(activity, 3, RecyclerView.VERTICAL, false)
        animeList.layoutManager = mLayoutManager

        animeList.setHasFixedSize(true)

        GlobalScope.launch(Dispatchers.Main) {
            val animes = AnimoDb.service
                .getAnimeList()
                .await()
            containerProgressIndicator.visibility = GONE
            adapter.animes = animes.data
        }

        animeList.adapter = adapter
    }

    fun navigateTo(fragment: BaseFragment, addToBackStack: Boolean) {
        val manager = activity?.supportFragmentManager

        if (!addToBackStack) {
            manager?.popBackStackImmediate()
        }

        val fragmentTransaction = manager?.beginTransaction()

        if (mFragment == null) {
            fragmentTransaction?.add(R.id.fragment_container, fragment)?.commit()

        } else {

            /*fragmentTransaction.setCustomAnimations(
                R.anim.enter_from_right, R.anim.exit_to_left,
                R.anim.enter_from_left, R.anim.exit_to_right
            )*/
            fragmentTransaction?.replace(R.id.fragment_container, fragment)
            fragmentTransaction?.addToBackStack(null)
            fragmentTransaction?.commit()


        }

        mFragment = fragment
    }

    companion object {
        fun newInstance(): Fragment {
            return AnimoFragment()
        }
    }
}