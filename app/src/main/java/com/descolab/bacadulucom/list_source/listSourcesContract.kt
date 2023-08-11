package com.descolab.bacadulucom.list_source

import com.descolab.bacadulucom.service.response.SourcesItem

class listSourcesContract {
    interface View{
        fun showListSources(data: ArrayList<SourcesItem>)
        fun showProgressDialog(show: Boolean)
    }

    interface UserActionListener{
        fun loadListSources(category: String)
    }
}