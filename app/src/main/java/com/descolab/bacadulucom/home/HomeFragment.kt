package com.descolab.bacadulucom.home

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.descolab.bacadulucom.R
import com.descolab.bacadulucom.detail_article.detailArticleActivity
import com.descolab.bacadulucom.service.response.ArticlesItem
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.Timer
import java.util.TimerTask

class HomeFragment : Fragment(),TopHeadlineAdapter.ListTopHeadlineListener,HomeContract.View {
    private var progressDialog : ProgressDialog? = null
    private var mActionListener: HomePresenter? = null
    private var mAdapter: TopHeadlineAdapter? = null
    lateinit var adapterBanner: BannerAdapter
    lateinit var listBanner: ArrayList<ArticlesItem>
    var currentIndex:Int=0;
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressDialog = ProgressDialog(context)
        progressDialog?.setMessage("Loading...")
        progressDialog?.setCancelable(false)

        mActionListener = context?.let { HomePresenter(it, this) }
        mActionListener?.loadTopHeadline()
        listBanner = ArrayList()
        val handler = Handler()

        val Update = Runnable {
            if (currentIndex === listBanner.size) {
                currentIndex = 0
            }
            view_pager_banner.setCurrentItem(currentIndex, true)
        }
        val swipeTimer = Timer()
        swipeTimer.schedule(object : TimerTask() {
            override fun run() {
                handler.post(Update)
            }
        }, 3000, 3000)


        view_pager_banner.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                updatePageIndicator(position)
            }
        })

    }


    override fun showTopHeadline(data: ArrayList<ArticlesItem>) {
        mAdapter = context?.let {
            TopHeadlineAdapter(it, data, this)
        }
        for (i in 1..5){
            listBanner.add(data.get(i))
        }
        adapterBanner = activity?.let { BannerAdapter(it,listBanner) }!!
        view_pager_banner.adapter = adapterBanner
        addPageIndicators()
        rvTopHeadline?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rvTopHeadline?.setHasFixedSize(true)
        rvTopHeadline?.adapter = mAdapter
    }

    override fun showProgressDialog(show: Boolean) {
        if (show) progressDialog?.show()
        else progressDialog?.dismiss()
    }

    override fun toDetailTopHeadline(item: ArticlesItem) {
        val i = Intent(this.context, detailArticleActivity::class.java)
        i.putExtra("title",item.title.toString())
        i.putExtra("content",item.content.toString())
        i.putExtra("publishedAt",item.publishedAt.toString())
        i.putExtra("urlToImage",item.urlToImage.toString())
        this.startActivity(i)
    }


    fun addPageIndicators()
    {
        lyt_page_indicator.removeAllViews()
        for (i in listBanner.indices) {
            val view = ImageView(activity)
            view.setImageResource(R.drawable.inactive)

            lyt_page_indicator.addView(view)
        }
        updatePageIndicator(currentIndex)
    }

    fun updatePageIndicator(position: Int) {
        var imageView: ImageView

        val lp =
            LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        lp.setMargins(10, 0, 10, 0)
        for (i in 0 until lyt_page_indicator.getChildCount()) {
            imageView = lyt_page_indicator.getChildAt(i) as ImageView
            imageView.setLayoutParams(lp)
            if (position == i) {
                imageView.setImageResource(R.drawable.active)
            } else {
                imageView.setImageResource(R.drawable.inactive)
            }
        }
    }


}