package com.descolab.bacadulucom.search

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

class SearchPresenter (val context: Context,
                       val mView: SearchContract.View)
    : BasePresenter(), SearchContract.UserActionListener {
    private val apiService : ApiService = ApiClient.getClient().create(ApiService::class.java)
    override fun searchArticlebyQuery(query: String) {
        val params = HashMap<String, String>()
        params["sources"] = query
        params["qInTitle"] = query
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

    override fun searchArticle() {
        val params = HashMap<String, String>()
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