package com.descolab.bacadulucom.search

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.descolab.bacadulucom.R
import com.descolab.bacadulucom.detail_article.detailArticleActivity
import com.descolab.bacadulucom.helper.Utils
import com.descolab.bacadulucom.service.response.ArticlesItem
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity(),SearchContract.View, SearchAdapter.ListArticleListener {
    private var mActionListener: SearchPresenter? = null
    private var progressDialog : ProgressDialog? = null
    private var mAdapterlistArticle: SearchAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        progressDialog = ProgressDialog(this)
        progressDialog?.setMessage("Loading...")
        progressDialog?.setCancelable(false)
        mActionListener = SearchPresenter(this, this)
        val keyword = intent.getStringExtra("keyword")
        if(keyword!=null){
            mActionListener?.searchArticlebyQuery(keyword)
        }else{
            mActionListener?.searchArticle()
        }
    }

    override fun showListArticle(data: ArrayList<ArticlesItem>) {
        if(data.isNotEmpty()){
            mAdapterlistArticle = SearchAdapter(this, data, this)
            rvSearch.layoutManager = LinearLayoutManager(this)
            rvSearch.adapter = mAdapterlistArticle
        }else{
            Utils.showToast(this, getString(R.string.data_notfound))
            finish()
        }
    }

    override fun backTo() {
        val builder = AlertDialog.Builder(this@SearchActivity)
        builder.setTitle(getString(R.string.app_name))
        builder.setMessage(getString(R.string.data_notfound))
        builder.setCancelable(false)
        builder.setPositiveButton("OK"){dialog, which ->
            super.onBackPressed()

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