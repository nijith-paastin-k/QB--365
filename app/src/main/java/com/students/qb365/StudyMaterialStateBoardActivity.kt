package com.students.qb365

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.students.qb365.databinding.ActivityStudyMaterialStateBoardBinding

class StudyMaterialStateBoardActivity : AppCompatActivity() {
    private var binding: ActivityStudyMaterialStateBoardBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudyMaterialStateBoardBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val subName = "TamilNadu StateBoard"

        binding?.apply {


            ivHeading.text = subName
            ivBack.setOnClickListener {
                onBackPressed()
            }
            tamilmedium.setOnClickListener{
                val intent = Intent(this@StudyMaterialStateBoardActivity,
                    StudyMaterialStateboardsixthActivity::class.java)
                startActivity(intent)
            }
            seventh.setOnClickListener{
                val intent = Intent(this@StudyMaterialStateBoardActivity,
                    StudyMaterialStateBoardSeventhActivity::class.java)
                startActivity(intent)
            }
            eight.setOnClickListener{
                val intent = Intent(this@StudyMaterialStateBoardActivity,
                    StudyMaterialStateBoardEightStandardActivity::class.java)
                startActivity(intent)
            }
            nineth.setOnClickListener{
                val intent = Intent(this@StudyMaterialStateBoardActivity,
                    StudyMaterialStateBoardNinethStandardActivity::class.java)
                startActivity(intent)
            }
            tenth.setOnClickListener{
                val intent = Intent(this@StudyMaterialStateBoardActivity,
                    StudyMaterialStateBoardTenthStandardActivity::class.java)
                startActivity(intent)
            }
            eleventh.setOnClickListener{
                val intent = Intent(this@StudyMaterialStateBoardActivity,
                    StudyMaterialStateBoardEleventhStandardActivity::class.java)
                startActivity(intent)
            }
            twelth.setOnClickListener{
                val intent = Intent(this@StudyMaterialStateBoardActivity,
                    StudyMaterialStateBoardTwelthStandardActivity::class.java)
                startActivity(intent)
            }


        }
    }
    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}