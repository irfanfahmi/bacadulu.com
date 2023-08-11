package com.descolab.bacadulucom.search

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.descolab.bacadulucom.R
import com.descolab.bacadulucom.helper.Tools
import com.descolab.bacadulucom.service.response.ArticlesItem
import kotlinx.android.synthetic.main.item_list_article.view.*

class SearchAdapter(
    private val mContext: Context,
    val mItems: ArrayList<ArticlesItem>,
    val listener: ListArticleListener
) :
    RecyclerView.Adapter<SearchAdapter.ViewHolder>() {
    private val mInflater: LayoutInflater

    init {
        mInflater = LayoutInflater.from(mContext)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchAdapter.ViewHolder {
        val view = mInflater.inflate(R.layout.item_list_search, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mItems.size
        //return 5
    }

    override fun onBindViewHolder(holder: SearchAdapter.ViewHolder, position: Int) {
        if (0 <= position && position < mItems.size) {
            val data = mItems[position]
            // Bind your data here
            holder.bind(data)
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: ArticlesItem) {
            itemView.tvTitle.text = data.title.toString()
            itemView.tvDate.text = Tools.getCreatedDate(data.publishedAt.toString())
            itemView.tvAuthor.text = data.author.toString()

            itemView.ivGambar?.let{
                Tools.displayImageOriginal(mContext, it, data.urlToImage.toString())
            }

            itemView.setOnClickListener {
                listener.toDetailArticle(data)
            }
        }
    }
    interface ListArticleListener {
        fun toDetailArticle(item: ArticlesItem)
    }



}