package com.nosti.animo.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nosti.animo.R
import com.nosti.animo.model.AnimoRepository
import com.nosti.animo.ui.detail.DetailFragment
import com.nosti.animo.ui.inflate
import kotlinx.android.synthetic.main.fragment_animo.*

class AnimoFragment : Fragment() {

    private lateinit var viewModel: AnimoViewModel
    private lateinit var adapter: AnimoAdapter

    private var mFragment: Fragment? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.fragment_animo)
    }

    @SuppressLint("WrongConstant")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(
            this,
            AnimoViewModelFactory(AnimoRepository())
        )[AnimoViewModel::class.java]

        val mLayoutManager = GridLayoutManager(activity, 3, RecyclerView.VERTICAL, false)
        animeList.layoutManager = mLayoutManager

        animeList.setHasFixedSize(true)

        adapter = AnimoAdapter(viewModel::onAnimeClicked)
        animeList.adapter = adapter
        viewModel.model.observe(this, Observer (::updateUi))
    }

    private fun updateUi(model: AnimoViewModel.UiModel) {

        containerProgressIndicator.visibility = if (model is AnimoViewModel.UiModel.Loading) View.VISIBLE else View.GONE

        when (model) {
            is AnimoViewModel.UiModel.Content -> adapter.animes = model.animes
            is AnimoViewModel.UiModel.Navigation -> {
                navigateTo(DetailFragment.newInstanceWithArguments(model.anime), false)
            }
        }
    }

    fun navigateTo(fragment: Fragment, addToBackStack: Boolean) {
        val manager = activity?.supportFragmentManager

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