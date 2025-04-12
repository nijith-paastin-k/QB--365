package com.students.qb365.ui.auth.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.students.qb365.databinding.ActivityBankTransferBinding

class BankTransferActivity : AppCompatActivity() {

    private var binding: ActivityBankTransferBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBankTransferBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        binding?.apply {
            btnBack.setOnClickListener {
                onBackPressed()
            }
        }
    }
}