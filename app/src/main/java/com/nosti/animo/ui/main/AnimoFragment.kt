package com.nosti.animo.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nosti.animo.R
import com.nosti.animo.model.server.AnimoRepository
import com.nosti.animo.ui.getViewModel
import com.nosti.animo.ui.inflate
import kotlinx.android.synthetic.main.fragment_animo.*

class AnimoFragment : Fragment() {

    private lateinit var viewModel: AnimoViewModel
    private lateinit var adapter: AnimoAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.fragment_animo)
    }

    @SuppressLint("WrongConstant")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = getViewModel { AnimoViewModel(AnimoRepository())}

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
                //Passing arguments with NavDirections
                val animeDetailArg = model.anime
                val action = AnimoFragmentDirections.actionAnimoFragmentToDetailFragment(animeDetailArg)
                findNavController(this).navigate(action)
            }
        }
    }
}