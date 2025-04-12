package com.students.qb365.ui.auth.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.students.qb365.utils.DialogUtil
import com.students.qb365.databinding.ActivityPurchaseTypeBinding
import com.students.qb365.listener.OnClickListener
import com.students.qb365.ui.auth.adapter.PurchaseTypeAdapter
import com.students.qb365.ui.auth.model.Type
import com.students.qb365.ui.auth.viewmodel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PurchaseTypeActivity : AppCompatActivity(), OnClickListener<Type> {

    private var binding: ActivityPurchaseTypeBinding? = null

    private val model: AuthViewModel by viewModels()

    private lateinit var boardId: String

    private lateinit var packID: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPurchaseTypeBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        packID = intent.getStringExtra("packID")!!
        boardId = intent.getStringExtra("boardId")!!
        val packName = intent.getStringExtra("packName")

        binding?.apply {

            tvHeader.text = packName

            rvPurchaseType.layoutManager = LinearLayoutManager(this@PurchaseTypeActivity)

            DialogUtil.showProgressDialog(this@PurchaseTypeActivity)
            model.getPurchaseType({ purchaseType ->
                DialogUtil.dismissProgressDialog()

                val adapter = PurchaseTypeAdapter(purchaseType.type, this@PurchaseTypeActivity)

                rvPurchaseType.adapter = adapter


            }, { s ->
                DialogUtil.dismissProgressDialog()

            })

            ivBack.setOnClickListener {
                onBackPressed()
            }

        }


    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    override fun onClick(t: Type, position: Int) {
        val intent = Intent(this, SubjectsActivity::class.java)
        intent.putExtra("typeID", t.id.toString())
        intent.putExtra("packID", packID)
        intent.putExtra("boardId", boardId)
        intent.putExtra("name", t.name)
        startActivity(intent)
    }
}