package com.descolab.bacadulucom.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.descolab.bacadulucom.R
import com.descolab.bacadulucom.helper.Tools
import com.descolab.bacadulucom.service.response.ArticlesItem
import kotlinx.android.synthetic.main.item_top_headline.view.*

class TopHeadlineAdapter(private val mContext: Context,
                         val mItems: ArrayList<ArticlesItem>,
                         val listener: ListTopHeadlineListener) :
    RecyclerView.Adapter<TopHeadlineAdapter.ViewHolder>() {
    private val mInflater: LayoutInflater

    init {
        mInflater = LayoutInflater.from(mContext)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopHeadlineAdapter.ViewHolder {
        val view = mInflater.inflate(R.layout.item_top_headline, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
       return mItems.size
        //return 5
    }

    override fun onBindViewHolder(holder: TopHeadlineAdapter.ViewHolder, position: Int) {
        if (0 <= position && position < mItems.size) {
            val data = mItems[position]
            // Bind your data here
            holder.bind(data)
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: ArticlesItem) {
            itemView.tvTitle.text = data.title.toString()
            itemView.tvDate.text =  Tools.getCreatedDate(data.publishedAt.toString())

            itemView.ivGambar?.let{
                Tools.displayImageOriginal(mContext,it,data.urlToImage.toString())
            }

            itemView.setOnClickListener {
                listener.toDetailTopHeadline(data)
            }
        }
    }
    interface ListTopHeadlineListener {
        fun toDetailTopHeadline(item: ArticlesItem)
    }
}