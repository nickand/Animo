package com.nosti.animo.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nosti.animo.R
import com.nosti.animo.ui.common.inflate
import com.nosti.animo.ui.detail.DetailFragment
import com.nosti.animo.ui.favorite.FavoriteAdapter
import com.nosti.animo.ui.favorite.FavoriteViewModel
import com.nosti.animo.ui.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_animo.*
import org.koin.android.scope.currentScope
import org.koin.android.viewmodel.ext.android.viewModel

class AnimoFragment : Fragment() {

    private lateinit var adapter: AnimoAdapter

    private val viewModel: AnimoViewModel by currentScope.viewModel(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.fragment_animo)
    }

    @SuppressLint("WrongConstant")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mLayoutManager = GridLayoutManager(activity, 3, RecyclerView.VERTICAL, false)
        animeList.layoutManager = mLayoutManager

        animeList.setHasFixedSize(true)

        adapter = AnimoAdapter(viewModel::onAnimeClicked)
        animeList.adapter = adapter
        viewModel.model.observe(this, Observer(::updateUi))
    }

    private fun updateUi(model: AnimoViewModel.UiModel) {

        containerProgressIndicator.visibility = if (model is AnimoViewModel.UiModel.Loading) View.VISIBLE else View.GONE

        when (model) {
            is AnimoViewModel.UiModel.Content -> adapter.animes = model.animes
            is AnimoViewModel.UiModel.Navigation -> {
                requireActivity().intent.putExtra(DetailFragment.ANIME, model.anime.id)
                Navigation.addFragment(requireActivity().supportFragmentManager, R.id.fragmentContainer, DetailFragment())

            }
            AnimoViewModel.UiModel.showUi -> {
                viewModel.showUi()
            }
        }
    }
}