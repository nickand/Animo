package com.nosti.animo.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nosti.animo.R
import com.nosti.animo.adapters.AppsListRecyclerViewAdapter
import com.nosti.animo.extensions.inflate
import com.nosti.animo.listeners.OnClickActivityListener
import com.nosti.animo.models.Anime
import com.nosti.animo.models.AnimeAttributes
import com.nosti.animo.models.AnimeData
import com.nosti.animo.network.AnimeApiClient
import com.nosti.animo.network.AnimeApiResponse
import com.nosti.animo.utils.Constants
import com.nosti.animo.utils.Utils
import kotlinx.android.synthetic.main.fragment_apps.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AppsFragment : BaseFragment() {
    private var mAdapter: AppsListRecyclerViewAdapter? = null
    private var things: List<AnimeData> = ArrayList()
    private var mListener: OnClickActivityListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return initViews(container)
    }

    @SuppressLint("WrongConstant")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setListeners()

        setErrorConnectionOrShowPosts()

        val mLayoutManager = GridLayoutManager(activity, 3, LinearLayoutManager.VERTICAL, false)
        animeList.layoutManager = mLayoutManager

        animeList.setHasFixedSize(true)
        mAdapter = mListener?.let { AppsListRecyclerViewAdapter(things, it, Constants.TYPE_FROM_APP_VIEW) }
        animeList.adapter = mAdapter
    }

    private fun setListeners() {
        animeList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0 || dy < 0 && fab.isShown) {
                    fab.hide()
                }
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    fab.show()
                }

                super.onScrollStateChanged(recyclerView, newState)
            }
        })
    }

    private fun setErrorConnectionOrShowPosts() {
        if (things.isEmpty()) {
            if (Utils.isNetworkConnected(activity)) {
                containerProgressIndicator.visibility = View.VISIBLE
                getPostsRedditFromApiResponse()
            } else {
                containerProgressIndicator.visibility = View.GONE
                containerNoInternetMessage.visibility = View.VISIBLE
            }
        } else {
            mAdapter = mListener?.let { AppsListRecyclerViewAdapter(things, it, Constants.TYPE_FROM_APP_VIEW) }
            animeList.adapter = mAdapter
        }
    }

    private fun initViews(container: ViewGroup?): View? {
        val rootView = container?.inflate(R.layout.fragment_apps)

        activity?.getString(R.string.app_name)?.let { mListener!!.setTitleToolbar(it) }

        //fab.setOnClickListener(View.OnClickListener { openCategoriesActivity() })

        return rootView
    }

    /*private fun openCategoriesActivity() {
        val i = Intent(activity, CategoriesActivity::class)
        i.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(i)
    }*/

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnClickActivityListener) {
            mListener = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnClickActivityListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    private fun getPostsRedditFromApiResponse() {
        val apiService = AnimeApiClient.getClient().create(AnimeApiResponse::class.java)
        val call = apiService.animeList
        call.enqueue(object : Callback<Anime> {
            override fun onResponse(call: Call<Anime>, response: Response<Anime>) {
                val apiResponse = response.body()

                val animeAttributes = ArrayList<AnimeAttributes>()
                for (i in 0 until apiResponse?.data?.size!!) {

                    Log.e("APPSFRAGMENT", "TEST API: " + apiResponse.data)
                    apiResponse.data[i]?.attributes?.let { animeAttributes.add(it) }
                }

                if (response.isSuccessful) {
                    containerProgressIndicator.visibility = View.GONE

                    mAdapter = mListener?.let {
                        AppsListRecyclerViewAdapter(
                            apiResponse.data,
                            it,
                            Constants.TYPE_FROM_APP_VIEW
                        )
                    }
                    animeList.adapter = mAdapter
                }
            }

            override fun onFailure(call: Call<Anime>, t: Throwable) {
                t.printStackTrace()
            }
        })

    }

    companion object {
        fun newInstance(): Fragment {
            return AppsFragment()
        }
    }
}