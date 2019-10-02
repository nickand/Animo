package com.nosti.animo.ui.search

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nosti.animo.R
import com.nosti.animo.ui.common.inflate
import com.nosti.animo.ui.detail.DetailFragment
import com.nosti.animo.ui.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_search.*
import org.koin.android.scope.currentScope
import org.koin.android.viewmodel.ext.android.viewModel

class SearchFragment : Fragment() {

    private var textSearch = ""
    private var actionSearch = false
    private lateinit var adapter: SearchAdapter

    private val viewModel: SearchViewModel by currentScope.viewModel(this)

    companion object {
        const val ANIME = "SearchFragment:anime"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.fragment_search)
    }

    @SuppressLint("WrongConstant")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mLayoutManager = GridLayoutManager(activity, 3, RecyclerView.VERTICAL, false)
        rvSearchAnimeList.layoutManager = mLayoutManager

        rvSearchAnimeList.setHasFixedSize(true)

        adapter = SearchAdapter(viewModel::onAnimeClicked)
        rvSearchAnimeList.adapter = adapter
        viewModel.model.observe(this, Observer(::updateUi))
        performSearch()
    }

    private fun updateUi(model: SearchViewModel.UiModel) {

        when (model) {
            is SearchViewModel.UiModel.Content -> adapter.animes = model.animes
            is SearchViewModel.UiModel.Navigation -> {
                requireActivity().intent.putExtra(ANIME, model.anime.id)
                Navigation.addFragment(requireActivity().supportFragmentManager, R.id.fragmentContainer, DetailFragment())
            }
            SearchViewModel.UiModel.showUi -> {
                if (textSearch.isNotEmpty()) {
                    viewModel.loadDataByTitle(textSearch)
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }

    private fun performSearch() {
        searchEditText.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(text: TextView, actionId: Int, event: KeyEvent?): Boolean {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {

                    actionSearch = true

                    if (!adapter.isEmpty()) {
                        adapter.clear()
                    }

                    textSearch = text.text.toString()
                    hideSoftKeyboard(searchEditText)

                    viewModel.loadDataByTitle(textSearch)
                    return true
                }

                return false
            }
        })
    }

    private fun showSoftKeyboard(input: EditText) {
        actionSearch = false
        val imm = activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(input, 0)
    }

    private fun hideSoftKeyboard(input: EditText) {
        actionSearch = false
        val imm = activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(input.windowToken, 0)
    }
}