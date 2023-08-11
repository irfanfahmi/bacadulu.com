package com.descolab.bacadulucom.list_source

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.descolab.bacadulucom.R
import com.descolab.bacadulucom.list_article.listArticleActivity
import com.descolab.bacadulucom.service.response.SourcesItem
import kotlinx.android.synthetic.main.activity_list_sources.*

class listSourcesActivity : AppCompatActivity(),listSourcesContract.View,listSourcesAdapter.ListSourcesListener {
    private var mActionListener: listSourcesPresenter? = null
    private var progressDialog : ProgressDialog? = null
    private var mAdapterlistSourcesAdapter: listSourcesAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_sources)
        val category :String? = intent.getStringExtra("category")
        progressDialog = ProgressDialog(this)
        progressDialog?.setMessage("Loading...")
        progressDialog?.setCancelable(false)
        mActionListener = listSourcesPresenter( this,this)
        if (category != null) {
            mActionListener?.loadListSources(category)
        }
    }

    override fun showListSources(data: ArrayList<SourcesItem>) {
        mAdapterlistSourcesAdapter = listSourcesAdapter(this,data,this)
        rvListSources?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvListSources?.setHasFixedSize(true)
        rvListSources?.adapter = mAdapterlistSourcesAdapter
    }

    override fun showProgressDialog(show: Boolean) {
        if (show) progressDialog?.show()
        else progressDialog?.dismiss()
    }

    override fun toNewsArticle(item: SourcesItem) {
        val i = Intent(this, listArticleActivity::class.java)
        i.putExtra("sources",item.name.toString())
        this.startActivity(i)
    }
}