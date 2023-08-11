package com.descolab.bacadulucom.category

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.descolab.bacadulucom.R
import com.descolab.bacadulucom.service.response.Category
import kotlinx.android.synthetic.main.item_category.view.*

class CategoryAdapter(private val mContext: Context,
                      val mItems: ArrayList<Category>,
                      val listener: ListCategoryListener) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
    private val mInflater: LayoutInflater

    init {
        mInflater = LayoutInflater.from(mContext)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryAdapter.ViewHolder {
        val view = mInflater.inflate(R.layout.item_category, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
       return mItems.size
        //return 5
    }

    override fun onBindViewHolder(holder: CategoryAdapter.ViewHolder, position: Int) {
        if (0 <= position && position < mItems.size) {
            val data = mItems[position]
            // Bind your data here
            holder.bind(data)
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: Category) {
            itemView.tvCategoryName.text = data.title.toString()

            itemView.setOnClickListener {
                listener.toDetailCategory(data)
            }
        }
    }
    interface ListCategoryListener {
        fun toDetailCategory(item: Category)
    }
}