package com.descolab.bacadulucom.home

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.descolab.bacadulucom.R
import com.descolab.bacadulucom.helper.Tools
import com.descolab.bacadulucom.service.response.ArticlesItem


class BannerAdapter(internal var context: Context, internal var itemList: ArrayList<ArticlesItem>) : PagerAdapter() {

    internal var mLayoutInflater: LayoutInflater
    private var pos = 0

    init {
        mLayoutInflater = this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    override fun getCount(): Int {
        return itemList.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val holder = ViewHolder()
        val itemView = mLayoutInflater.inflate(R.layout.item_banner, container, false)
        holder.itemImage = itemView.findViewById(R.id.img_slider) as ImageView
        holder.tvTitleBanner = itemView.findViewById(R.id.tvTitleBanner) as TextView

        if (pos > this.itemList.size - 1)
            pos = 0

        holder.sliderItem = this.itemList[pos]
        Tools.displayImageOriginal(context,holder.itemImage,itemList.get(pos).urlToImage.toString())
        holder.tvTitleBanner.text = itemList.get(pos).title.toString()


        (container as ViewPager).addView(itemView)

        holder.itemImage.scaleType = ImageView.ScaleType.FIT_XY

        pos++
        return itemView
    }

    internal class ViewHolder {
        lateinit var sliderItem: ArticlesItem
        lateinit var itemImage: ImageView
        lateinit var tvTitleBanner: TextView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as ConstraintLayout)
    }

    override fun isViewFromObject(arg0: View, arg1: Any): Boolean {
        return arg0 === arg1 as View
    }

    override fun destroyItem(arg0: View, arg1: Int, arg2: Any) {
        (arg0 as ViewPager).removeView(arg2 as View)
    }
}