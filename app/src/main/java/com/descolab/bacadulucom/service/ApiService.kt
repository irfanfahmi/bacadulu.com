package com.descolab.bacadulucom.service

import com.descolab.bacadulucom.BuildConfig
import com.descolab.bacadulucom.service.response.ArticlesItem
import com.descolab.bacadulucom.service.response.ResponseSources
import com.descolab.bacadulucom.service.response.SourcesItem
import com.descolab.bacadulucom.service.responsee.ResponseArticle
import retrofit2.Call
import retrofit2.http.*
import java.util.*


interface ApiService {

    @GET("top-headlines?country=us&apiKey=${BuildConfig.API_KEY}")
    fun getTopHeadline(): Call<ResponseArticle<ArrayList<ArticlesItem>>>

    @GET("sources?apiKey=${BuildConfig.API_KEY}")
    fun getSources(
        @QueryMap params: Map<String, String>
    ): Call<ResponseSources<ArrayList<SourcesItem>>>

    @GET("everything?apiKey=${BuildConfig.API_KEY}")
    fun getArticle(
        @QueryMap params: Map<String, String>
    ): Call<ResponseArticle<ArrayList<ArticlesItem>>>



}
