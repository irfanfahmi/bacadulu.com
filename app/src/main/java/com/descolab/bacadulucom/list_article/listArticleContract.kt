package com.descolab.bacadulucom.list_article

import com.descolab.bacadulucom.service.response.ArticlesItem

class listArticleContract {

    interface View{
        fun showListArticle(data: ArrayList<ArticlesItem>)
        fun backTo()
        fun showProgressDialog(show: Boolean)
    }

    interface UserActionListener{
        fun loadListArticle(sources: String)
    }
}