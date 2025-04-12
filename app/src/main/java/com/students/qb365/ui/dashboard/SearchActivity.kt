package com.students.qb365.ui.dashboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.students.qb365.R
import com.students.qb365.databinding.ActivitySearchBinding
import com.students.qb365.ui.dashboard.adapter.SearchAdapter
import com.students.qb365.ui.dashboard.model.SearchData
import com.students.qb365.utils.DialogUtil
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchActivity : AppCompatActivity() {

    private var binding: ActivitySearchBinding? = null

    private val model: MainViewModel by viewModels()

    private var search = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        search = intent.getStringExtra("search")!!

        binding?.apply {

            rvSearch.layoutManager = LinearLayoutManager(this@SearchActivity)

            DialogUtil.showProgressDialog(this@SearchActivity)
            model.search(search, { search ->
                DialogUtil.dismissProgressDialog()

                val finalList = ArrayList<SearchData>()



                for (datum in search.data) {
                    finalList
                }

                val adapter = SearchAdapter(search.data)
                rvSearch.adapter = adapter

            }, { s ->
                DialogUtil.dismissProgressDialog()
            })

            ivBack.setOnClickListener {
                onBackPressed()
            }
        }


    }
}