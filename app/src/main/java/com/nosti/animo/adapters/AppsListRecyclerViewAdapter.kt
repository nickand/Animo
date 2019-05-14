package com.nosti.animo.adapters

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.nosti.animo.R
import com.nosti.animo.fragments.DetailFragment
import com.nosti.animo.listeners.OnClickActivityListener
import com.nosti.animo.models.AnimeData
import com.nosti.animo.utils.Constants

class AppsListRecyclerViewAdapter(items: List<AnimeData?>, listener: OnClickActivityListener, viewType: Int) :
    RecyclerView.Adapter<AppsListRecyclerViewAdapter.ViewHolder>() {

    private var mValues: List<AnimeData?> = items
    private var mListener: OnClickActivityListener? = listener
    private var mContext: Context? = null
    private var mViewType: Int = viewType

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_scalable, parent, false)
        mContext = parent.context
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.mItem = mValues[position]

        /*holder.mAppName.setText(holder.mItem.getAnimeAttributes().getCanonicalTitle());
        holder.mAppCompany.setText(holder.mItem.getAnimeAttributes().getAverageRating());
        holder.mAppCategory.setText(holder.mItem.getAnimeAttributes().getStartDate());*/

        if (mViewType == Constants.TYPE_FROM_APP_VIEW) {
            val uri: Uri = Uri.parse(holder.mItem!!.attributes.posterImage?.medium)

            Glide.with(holder.itemView.context)
                .load(uri)
                .transition(DrawableTransitionOptions.withCrossFade())
                .apply(
                    RequestOptions()
                        .error(R.drawable.ic_error)
                )
                .into(holder.mAppImage)
        } else {
            holder.mAppImage.visibility = View.GONE
        }

        holder.mView.setOnClickListener {
            if (null != mListener) {
                when (mViewType) {
                    Constants.TYPE_FROM_APP_VIEW -> {
                        mListener!!.navigateTo(DetailFragment.newInstanceWithArguments(holder.mItem))
                    }
                    Constants.TYPE_FROM_BROWSER -> {
                    }
                }

                //mListener.openWebView((Activity) mContext, holder.mItem.attributes.);
            }
        }


    }

    override fun getItemCount(): Int {
        return mValues.size
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        var mAppImage: ImageView = mView.findViewById(R.id.thumbnail)

        var mItem: AnimeData? = null

    }
}