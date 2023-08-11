package com.descolab.bacadulucom.list_article

import android.content.Context
import com.descolab.bacadulucom.base.BasePresenter
import com.descolab.bacadulucom.service.ApiClient
import com.descolab.bacadulucom.service.ApiService
import com.descolab.bacadulucom.service.response.ArticlesItem
import com.descolab.bacadulucom.service.responsee.ResponseArticle
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.HashMap

class listArticlePresenter (val context: Context,
                            val mView: listArticleContract.View)
    : BasePresenter(), listArticleContract.UserActionListener {
    private val apiService : ApiService = ApiClient.getClient().create(ApiService::class.java)
    override fun loadListArticle(sources: String) {
        val params = HashMap<String, String>()
        params["sources"] = sources
        val call = apiService.getArticle(params)
        mView.showProgressDialog(true)
        call.enqueue(object : Callback<ResponseArticle<ArrayList<ArticlesItem>>> {
            override fun onResponse(call: Call<ResponseArticle<ArrayList<ArticlesItem>>>, responseArticle: Response<ResponseArticle<ArrayList<ArticlesItem>>>) {
                mView.showProgressDialog(false)
                val resource = responseArticle.body()
                if (responseArticle.isSuccessful) {
                    if (resource?.status.equals("ok")){
                        if (resource != null) {
                            resource.articles?.let { mView.showListArticle(it as ArrayList<ArticlesItem>) }
                        }
                    }else{
                        mView.backTo()
                    }
                } else {
                    mView.backTo()
                }
            }

            override fun onFailure(call: Call<ResponseArticle<ArrayList<ArticlesItem>>>, t: Throwable) {
                mView.showProgressDialog(false)
                call.cancel()
            }
        })

    }
}