package com.students.qb365.ui.navigationMenu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.students.qb365.databinding.ActivityMyOrderBinding
import com.students.qb365.ui.dashboard.MainViewModel
import com.students.qb365.utils.DialogUtil
import com.students.qb365.utils.errorToast
import com.students.qb365.utils.gone
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyOrderActivity : AppCompatActivity() {

    private var binding: ActivityMyOrderBinding? = null

    private val model: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyOrderBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.apply {

            btnBack.setOnClickListener { onBackPressed() }

            DialogUtil.showProgressDialog(this@MyOrderActivity)
            model.order({ orderDetail ->
                DialogUtil.dismissProgressDialog()

                val data = orderDetail.data

                if (orderDetail.status == true) {

                    tvCBSE.text = data?.board
                    tvOrderName.text = data?.qbsDeptName

                    tvValidity.text = data?.description.toString()
                    tvPayVia.text = data?.paymentMode
                    tvPrice.text = data?.amount + " INR"
                    tvDate.text = data?.date
                    tvPayVia.text = data?.paymentMode
                } else {
                    errorToast(orderDetail.message.toString())
                    card.gone()
                }

//                val adapter = OrderDetailAdapter(orderDetail.data?.)

            }, { s ->
                DialogUtil.dismissProgressDialog()
            })

            btnBack.setOnClickListener {
                onBackPressed()
            }

        }

    }
}