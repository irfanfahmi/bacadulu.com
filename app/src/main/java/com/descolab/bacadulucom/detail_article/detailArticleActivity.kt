package com.descolab.bacadulucom.detail_article

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.descolab.bacadulucom.R
import com.descolab.bacadulucom.helper.Tools
import kotlinx.android.synthetic.main.activity_detail_article.ivImage
import kotlinx.android.synthetic.main.activity_detail_article.tvContent
import kotlinx.android.synthetic.main.activity_detail_article.tvTime
import kotlinx.android.synthetic.main.activity_detail_article.tvTitle

class detailArticleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_article)

        val title :String? = intent.getStringExtra("title")
        val content :String? = intent.getStringExtra("content")
        val publishedAt :String? = intent.getStringExtra("publishedAt")
        val urlToImage :String? = intent.getStringExtra("urlToImage")

        tvTitle.text = title
        tvContent.text = content
        tvTime.text = publishedAt?.let { Tools.getCreatedDate(it) }
        Tools.displayImageOriginal(this,ivImage,urlToImage.toString())

    }
}