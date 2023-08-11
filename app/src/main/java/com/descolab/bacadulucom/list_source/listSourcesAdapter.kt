package com.descolab.bacadulucom.list_source

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.descolab.bacadulucom.R
import com.descolab.bacadulucom.helper.Tools
import com.descolab.bacadulucom.service.response.SourcesItem
import kotlinx.android.synthetic.main.item_list_sources.view.*

class listSourcesAdapter(private val mContext: Context,
                         val mItems: ArrayList<SourcesItem>,
                         val listener: ListSourcesListener) :
    RecyclerView.Adapter<listSourcesAdapter.ViewHolder>() {
    private val mInflater: LayoutInflater

    init {
        mInflater = LayoutInflater.from(mContext)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): listSourcesAdapter.ViewHolder {
        val view = mInflater.inflate(R.layout.item_list_sources, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
       return mItems.size
        //return 5
    }

    override fun onBindViewHolder(holder: listSourcesAdapter.ViewHolder, position: Int) {
        if (0 <= position && position < mItems.size) {
            val data = mItems[position]
            // Bind your data here
            holder.bind(data)
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: SourcesItem) {
            itemView.tvTitle.text = data.name.toString()
            itemView.tvDescription.text = data.description.toString()
            itemView.tvCategoryName.text = data.category.toString()
            itemView.ivGambar?.let{
                Tools.displayImageOriginal(mContext,it,R.drawable.ic_newspaper)
            }

            itemView.setOnClickListener {
                listener.toNewsArticle(data)
            }
        }
    }
    interface ListSourcesListener {
        fun toNewsArticle(item: SourcesItem)
    }
}