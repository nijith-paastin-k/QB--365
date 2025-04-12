package com.students.qb365.ui.dashboard.adapter

import android.animation.LayoutTransition
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.students.qb365.R
import com.students.qb365.ui.auth.model.UpcomingTest
import com.students.qb365.utils.load

class UpcomingTestPagerAdapter(
    private val list: ArrayList<ArrayList<UpcomingTest>>,
    private val onClickListener: OnClickListener
) :
    PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

//        val binding = UpcomingItemPagerLayoutBinding.inflate(
//            LayoutInflater.from(container.context),
//            container,
//            true
//        )
        val binding = LayoutInflater.from(container.context)
            .inflate(R.layout.upcoming_item_pager_layout, container, false)

        val image = binding.findViewById<ImageView>(R.id.image)
        val tvTitle = binding.findViewById<TextView>(R.id.tvTitle)
        val tvDate = binding.findViewById<TextView>(R.id.tvDate)

//        binding.apply {
        image.load(list[0][position].image!!)
        tvTitle.text = list[0][position].testName
        tvDate.text = list[0][position].date

        binding.setOnClickListener {
            onClickListener.onUpComingTest(list[0], position)
        }


//        }

        container.addView(binding)
        return binding
    }

    interface OnClickListener {
        fun onUpComingTest(list: ArrayList<UpcomingTest>, position: Int)
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun getCount(): Int = list[0].size

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }
}