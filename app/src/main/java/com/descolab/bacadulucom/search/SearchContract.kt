package com.descolab.bacadulucom.search

import com.descolab.bacadulucom.service.response.ArticlesItem

class SearchContract {
    interface View{
        fun showListArticle(data: ArrayList<ArticlesItem>)
        fun backTo()
        fun showProgressDialog(show: Boolean)
    }

    interface UserActionListener{
        fun searchArticlebyQuery(query: String)
        fun searchArticle()
    }
}