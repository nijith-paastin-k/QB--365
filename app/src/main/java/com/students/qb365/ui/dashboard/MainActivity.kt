package com.students.qb365.ui.dashboard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.students.qb365.databinding.ActivityMainBinding
import com.students.qb365.listener.OnClickListener
import com.students.qb365.ui.auth.model.TestPack
import com.students.qb365.ui.auth.model.UpcomingTest
import com.students.qb365.ui.auth.register.BoardActivity
import com.students.qb365.ui.dashboard.adapter.MainBoardAdapter
import com.students.qb365.ui.dashboard.adapter.TestPackAdapter
import com.students.qb365.ui.dashboard.adapter.UpcomingTestPagerAdapter
import com.students.qb365.ui.navigationMenu.InviteEarnActivity
import com.students.qb365.ui.subject_mode.writeTest.ExamInstructionActivity
import com.students.qb365.utils.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), OnClickListener<TestPack>,
    UpcomingTestPagerAdapter.OnClickListener {

    private var binding: ActivityMainBinding? = null

    private val model: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.apply {

            btnReferNow.setOnClickListener {
                openActivity(InviteEarnActivity::class.java)
            }

            userName.text =
                "Hey, " + SharedPrefs.getString(this@MainActivity, SharedPrefs.USER_NAME)

            rvTestPack.layoutManager = LinearLayoutManager(
                this@MainActivity, RecyclerView.HORIZONTAL, false
            )

            DialogUtil.showProgressDialog(this@MainActivity)
            val token =
                "Bearer " + SharedPrefs.getString(this@MainActivity, SharedPrefs.ACCESS_TOKEN)

            model.dashboard(token, { dashboard ->
                DialogUtil.dismissProgressDialog()

                val mainList = ArrayList<Any>()

                for (data in dashboard.data?.board!!) {
                    mainList.add(data.title!!)
                    mainList.add(data.details)
                }

                rvBoard.layoutManager = LinearLayoutManager(
                    this@MainActivity
                )
                val adapter = MainBoardAdapter(this@MainActivity, mainList)
                rvBoard.adapter = adapter


                //Test Pack
                val testPackList = dashboard.data?.testPack
                val testPackAdapter = TestPackAdapter(testPackList!!, this@MainActivity)
                rvTestPack.adapter = testPackAdapter


                //Upcoming Test Pack
                val upcomingTestList = dashboard.data!!.upcomingTest
                val upcomingTestPagerAdapter =
                    UpcomingTestPagerAdapter(upcomingTestList, this@MainActivity)
                upcomingViewpager.adapter = upcomingTestPagerAdapter

                upcomingViewpager.pageMargin = 60

            }, { s ->
                errorToast("Something went wrong")
                DialogUtil.dismissProgressDialog()

            })


            ivHamburger.setOnClickListener {
                val intent = Intent(this@MainActivity, NavigationActivity::class.java)
                startActivity(intent)
                overridePendingTransition(
                    android.R.anim.slide_in_left,
                    android.R.anim.slide_out_right
                )
            }

//            etSearch.setOnEditorActionListener { _, actionId, _ ->
//                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
//                    val intent = Intent(this@MainActivity, SearchActivity::class.java)
//                    intent.putExtra("search", etSearch.text.toString().trim())
//                    startActivity(intent)
//                    true
//                }
//                false
//            }

            icSearch.setOnClickListener {
                val intent = Intent(this@MainActivity, SearchActivity::class.java)
                intent.putExtra("search", etSearch.text.toString().trim())
                startActivity(intent)
            }

            ivCart.setOnClickListener {
                Constants.IS_CART = "Yes"
                openActivity(BoardActivity::class.java)
            }

        }

    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }


    override fun onClick(t: TestPack, position: Int) {
        openActivity(TestSubjectsActivity::class.java)
    }

    override fun onUpComingTest(list: ArrayList<UpcomingTest>, position: Int) {
        val boardId = SharedPrefs.getString(this, SharedPrefs.BOARD_ID)

        val intent = Intent(this, ExamInstructionActivity::class.java)
        intent.putExtra("testId", list[0].testId)
        intent.putExtra("boardId", boardId)
        intent.putExtra("testName", list[0].testName)
        startActivity(intent)
    }
}