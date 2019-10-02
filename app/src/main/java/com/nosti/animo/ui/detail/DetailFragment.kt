package com.nosti.animo.ui.detail

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getDrawable
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.nosti.animo.R
import com.nosti.animo.ui.OnNavigateListener
import com.nosti.animo.ui.common.inflate
import com.nosti.animo.ui.common.loadUrl
import com.nosti.animo.ui.search.SearchFragment
import kotlinx.android.synthetic.main.fragment_detail.*
import org.koin.android.scope.currentScope
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class DetailFragment : Fragment(), View.OnClickListener {

    companion object {
        private val CLASS_TAG = DetailFragment::class.java.simpleName
        const val ANIME = "DetailFragment:anime"

        fun newInstance(animeId: Int): DetailFragment {
            val bundle = Bundle()
            bundle.putInt(ANIME, animeId)
            return DetailFragment().apply {
                arguments = bundle
                retainInstance = true
            }
        }
    }

    private lateinit var animeId: String
    private lateinit var animeType: String
    private var isShowing = false

    private var mListener: OnNavigateListener? = null

    private val viewModel: DetailViewModel by currentScope.viewModel(this) {
        if (requireActivity().intent.hasExtra(SearchFragment.ANIME)) {
            animeId = requireActivity().intent.getIntExtra(SearchFragment.ANIME, -1).toString()
            requireActivity().intent.removeExtra(SearchFragment.ANIME)
            parametersOf(animeId.toDouble())
        } else {
            animeId = requireActivity().intent.getIntExtra(ANIME, -1).toString()
            parametersOf(animeId.toDouble())

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return initViews(container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.model.observe(this, Observer(::updateUi))

        setListeners()
    }

    private fun updateUi(model: DetailViewModel.UiModel) = with(model.anime) {
        btnGoToWeb.visibility = View.VISIBLE
        tvShowMore.visibility = View.VISIBLE
        tvTitleDescription.visibility = View.VISIBLE

        animeId = model.anime.id.toString()
        animeType = model.anime.type.toString()

        tvDetailAppName.text = model.anime.attributes?.canonicalTitle
        tvDetailDescription.text = model.anime.attributes?.synopsis

        tvDetailAppMadeBy.text = model.anime.attributes?.endDate

        val path = getCoverImage(model.anime.attributes?.coverImage?.large)

        ivCoverApp.loadUrl(path)
        ivDetailIconApp.loadUrl(model.anime.attributes?.posterImage?.medium.toString())

        val icon = if (favorite) R.drawable.ic_favorite else R.drawable.ic_favorite_border
        ivFavorite.setImageDrawable(activity?.applicationContext?.let { getDrawable(it, icon) })
    }

    private fun setListeners() {
        btnGoToWeb.setOnClickListener(this)
        tvShowMore.setOnClickListener(this)

        ivFavorite.setOnClickListener { viewModel.onFavoriteClicked() }
    }

    private fun initViews(container: ViewGroup?): View? {
        return container?.inflate(R.layout.fragment_detail)
    }

    private fun getCoverImage(coverImage: String?) = when (coverImage) {
        is String -> coverImage
        else -> "res:/" + R.drawable.img_me
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnNavigateListener) {
            mListener = context
        } else {
            throw RuntimeException("$context must implement OnSetTitleAndNavigateListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnGoToWeb -> {
                val type: String = animeType
                val id: String = animeId
                val url = "$type/$id"
                activity?.parent?.let { mListener!!.openWebView(it, "https://kitsu.io/$url") }
            }
            R.id.tvShowMore -> setShowMoreOrLess()
        }
    }

    private fun setShowMoreOrLess() {
        if (!isShowing) {
            isShowing = true
            tvDetailDescription.maxLines = Integer.MAX_VALUE
            tvShowMore.setText(R.string.show_less)
        } else {
            isShowing = false
            tvDetailDescription.maxLines = 4
            tvShowMore.setText(R.string.read_more)
        }
    }
}