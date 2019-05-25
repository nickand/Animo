package com.nosti.animo.ui.detail

import android.content.Context
import android.net.Uri
import android.net.Uri.parse
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.nosti.animo.R
import com.nosti.animo.model.AnimeData
import com.nosti.animo.ui.OnSetTitleAndNavigateListener
import com.nosti.animo.ui.getViewModel
import com.nosti.animo.ui.inflate
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : Fragment(), View.OnClickListener {
    private lateinit var viewModel: DetailViewModel

    private var data: AnimeData? = null
    private var isShowing = false

    private var mListener: OnSetTitleAndNavigateListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            Log.d(CLASS_TAG, " getting args")

            data = arguments.let {
                it?.let { argument -> DetailFragmentArgs.fromBundle(argument).animeDetail }
            }
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

        viewModel = getViewModel { data?.let { DetailViewModel(it) }!! }

        viewModel.model.observe(this, Observer (::updateUi))

        setListeners()
    }

    private fun updateUi(model: DetailViewModel.UiModel) {
        btnGoToWeb.visibility = View.VISIBLE
        tvShowMore.visibility = View.VISIBLE
        tvTitleDescription.visibility = View.VISIBLE

        tvDetailAppName.text = model.anime.attributes.canonicalTitle
        tvDetailDescription.text = model.anime.attributes.synopsis

        tvDetailAppMadeBy.text = data!!.attributes.endDate

        val uri: Uri = parse(model.anime.attributes.posterImage?.medium)
        val path = getCoverImage(model.anime.attributes.coverImage?.large)
        val uriCover: Uri = parse(path)

        activity?.applicationContext?.let {
            Glide.with(it)
                .load(uri)
                .transition(DrawableTransitionOptions.withCrossFade())
                .apply(
                    RequestOptions()
                        .error(R.drawable.ic_error)
                )
                .into(ivDetailIconApp)
        }

        activity?.applicationContext?.let {
            Glide.with(it)
                .load(uriCover)
                .transition(DrawableTransitionOptions.withCrossFade())
                .apply(
                    RequestOptions()
                        .error(R.drawable.ic_error)
                )
                .into(ivCoverApp)
        }
    }

    private fun setListeners() {
        mListener!!.setTitleToolbar(data!!.attributes.canonicalTitle)
        btnGoToWeb.setOnClickListener(this)
        tvShowMore.setOnClickListener(this)
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
        if (context is OnSetTitleAndNavigateListener) {
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
                val type: String = data!!.type
                val id: String = data!!.id.toString()
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

    companion object {
        private val CLASS_TAG = DetailFragment::class.java.simpleName
    }
}