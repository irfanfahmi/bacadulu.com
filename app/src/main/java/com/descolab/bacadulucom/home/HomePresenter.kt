package com.descolab.bacadulucom.home

import android.content.Context
import com.descolab.bacadulucom.base.BasePresenter
import com.descolab.bacadulucom.service.ApiClient
import com.descolab.bacadulucom.service.ApiService
import com.descolab.bacadulucom.service.response.ArticlesItem
import com.descolab.bacadulucom.service.responsee.ResponseArticle
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.collections.ArrayList

class HomePresenter (val context: Context,
                     val mView: HomeContract.View)
    : BasePresenter(), HomeContract.UserActionListener {
    private val apiService : ApiService = ApiClient.getClient().create(ApiService::class.java)

    override fun loadTopHeadline() {
        val call = apiService.getTopHeadline()
        mView.showProgressDialog(true)
        call.enqueue(object : Callback<ResponseArticle<ArrayList<ArticlesItem>>> {
            override fun onResponse(call: Call<ResponseArticle<ArrayList<ArticlesItem>>>, responseArticle: Response<ResponseArticle<ArrayList<ArticlesItem>>>) {
                mView.showProgressDialog(false)
                if (responseArticle.isSuccessful) {
                    val resource = responseArticle.body()
                    if (resource != null) {
                        resource.articles?.let { mView.showTopHeadline(it as ArrayList<ArticlesItem>) }
                    }

                } else {
                    showDialog(context, "Error")
                }
            }

            override fun onFailure(call: Call<ResponseArticle<ArrayList<ArticlesItem>>>, t: Throwable) {
                mView.showProgressDialog(false)
                call.cancel()
            }
        })
    }
}