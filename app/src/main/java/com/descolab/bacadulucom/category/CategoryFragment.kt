package com.descolab.bacadulucom.category

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.descolab.bacadulucom.R
import com.descolab.bacadulucom.home.CategoryAdapter
import com.descolab.bacadulucom.home.HomePresenter
import com.descolab.bacadulucom.home.TopHeadlineAdapter
import com.descolab.bacadulucom.list_source.listSourcesActivity
import com.descolab.bacadulucom.service.response.Category
import kotlinx.android.synthetic.main.fragment_category.*


class CategoryFragment : Fragment(),CategoryAdapter.ListCategoryListener {

    private var progressDialog : ProgressDialog? = null
    private var mActionListener: HomePresenter? = null
    private var mAdapter: TopHeadlineAdapter? = null
    private var mAdapterCategory: CategoryAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressDialog = ProgressDialog(context)
        progressDialog?.setMessage("Loading...")
        progressDialog?.setCancelable(false)
        setData()

    }

    private fun setData() {
        val data = arrayListOf(
            Category("business", context?.getString(R.string.business).toString()),
            Category("entertainment", context?.getString(R.string.entertainment).toString()),
            Category("general", context?.getString(R.string.general).toString()),
            Category("health", context?.getString(R.string.health).toString()),
            Category("science", context?.getString(R.string.science).toString()),
            Category("sports", context?.getString(R.string.sports).toString()),
            Category("technology", context?.getString(R.string.technology).toString()),
        )
        mAdapterCategory = context?.let {
            CategoryAdapter(it, data, this)
        }
        rvCategory?.layoutManager = GridLayoutManager(context,2, GridLayoutManager.VERTICAL, false)
        rvCategory?.setHasFixedSize(true)
        rvCategory?.adapter = mAdapterCategory
    }

    override fun toDetailCategory(item: Category) {
        val i = Intent(this.context, listSourcesActivity::class.java)
        i.putExtra("category",item.id.toString())
        this.startActivity(i)
    }


}