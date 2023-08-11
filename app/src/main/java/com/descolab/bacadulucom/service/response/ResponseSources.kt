package com.descolab.bacadulucom.service.response

import com.google.gson.annotations.SerializedName

data class ResponseSources<T>(

    @field:SerializedName("sources")
    val sources: List<SourcesItem?>? = null,

    @field:SerializedName("status")
    val status: String? = null
)