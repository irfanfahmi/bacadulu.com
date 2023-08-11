package com.descolab.bacadulucom.service.response

import com.google.gson.annotations.SerializedName

data class Source(

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("id")
    val id: Any? = null
)