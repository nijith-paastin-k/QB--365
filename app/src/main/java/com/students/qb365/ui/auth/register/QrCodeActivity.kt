package com.students.qb365.ui.auth.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.students.qb365.R
import com.students.qb365.databinding.ActivityQrCodeBinding

class QrCodeActivity : AppCompatActivity() {

    private var binding: ActivityQrCodeBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityQrCodeBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        binding?.apply {
            btnBack.setOnClickListener {
                onBackPressed()
            }
        }
    }
}