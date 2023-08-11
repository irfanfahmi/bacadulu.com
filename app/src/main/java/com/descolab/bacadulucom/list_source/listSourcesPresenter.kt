package com.descolab.bacadulucom.list_source

import android.content.Context
import com.descolab.bacadulucom.base.BasePresenter
import com.descolab.bacadulucom.service.ApiClient
import com.descolab.bacadulucom.service.ApiService
import com.descolab.bacadulucom.service.response.ResponseSources
import com.descolab.bacadulucom.service.response.SourcesItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.HashMap

class listSourcesPresenter (val context: Context,
                            val mView: listSourcesContract.View)
    : BasePresenter(), listSourcesContract.UserActionListener {
    private val apiService : ApiService = ApiClient.getClient().create(ApiService::class.java)

    override fun loadListSources(category: String) {
        val params = HashMap<String, String>()
        params["category"] = category
        val call = apiService.getSources(params)
        mView.showProgressDialog(true)
        call.enqueue(object : Callback<ResponseSources<ArrayList<SourcesItem>>> {
            override fun onResponse(call: Call<ResponseSources<ArrayList<SourcesItem>>>, responseArticle: Response<ResponseSources<ArrayList<SourcesItem>>>) {
                mView.showProgressDialog(false)
                if (responseArticle.isSuccessful) {
                    val resource = responseArticle.body()
                    if (resource != null) {
                        resource.sources?.let { mView.showListSources(it as ArrayList<SourcesItem>) }
                    }

                } else {
                    showDialog(context, "Error")
                }
            }

            override fun onFailure(call: Call<ResponseSources<ArrayList<SourcesItem>>>, t: Throwable) {
                mView.showProgressDialog(false)
                call.cancel()
            }
        })
    }

}