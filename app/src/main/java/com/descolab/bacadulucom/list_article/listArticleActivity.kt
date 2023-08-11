package com.descolab.bacadulucom.list_article

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.descolab.bacadulucom.R
import com.descolab.bacadulucom.detail_article.detailArticleActivity
import com.descolab.bacadulucom.service.response.ArticlesItem
import kotlinx.android.synthetic.main.activity_list_article.*

class listArticleActivity : AppCompatActivity(),listArticleContract.View,listArticleAdapter.ListArticleListener {
    private var mActionListener: listArticlePresenter? = null
    private var progressDialog : ProgressDialog? = null
    private var mAdapterlistArticleAdapter: listArticleAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_article)
        val sources :String? = intent.getStringExtra("sources")
        progressDialog = ProgressDialog(this)
        progressDialog?.setMessage("Loading...")
        progressDialog?.setCancelable(false)
        mActionListener = listArticlePresenter( this,this)
        if (sources != null) {
            mActionListener?.loadListArticle(sources)
        }

    }

    override fun showListArticle(data: ArrayList<ArticlesItem>) {
        mAdapterlistArticleAdapter = listArticleAdapter(this,data,this)
        rvListArticle?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvListArticle?.setHasFixedSize(true)
        rvListArticle?.adapter = mAdapterlistArticleAdapter
    }

    override fun backTo() {
        val builder = AlertDialog.Builder(this@listArticleActivity)
        builder.setTitle(getString(R.string.app_name))
        builder.setMessage(getString(R.string.no_sources))
        builder.setCancelable(false)
        builder.setPositiveButton("OK"){dialog, which ->
            super.onResume()
        }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    override fun showProgressDialog(show: Boolean) {
        if (show) progressDialog?.show()
        else progressDialog?.dismiss()
    }

    override fun toDetailArticle(item: ArticlesItem) {
        val i = Intent(this, detailArticleActivity::class.java)
        i.putExtra("url",item.url.toString())
        this.startActivity(i)
    }
}