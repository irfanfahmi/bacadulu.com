package com.descolab.bacadulucom.home

import com.descolab.bacadulucom.service.response.ArticlesItem

class HomeContract {
    interface View{
        fun showTopHeadline(data: ArrayList<ArticlesItem>)
        fun showProgressDialog(show: Boolean)
    }

    interface UserActionListener{
        fun loadTopHeadline()
    }
}