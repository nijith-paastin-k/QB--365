package com.students.qb365.ui.navigationMenu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import androidx.activity.viewModels
import com.canhub.cropper.CropImageContract
import com.canhub.cropper.CropImageView
import com.canhub.cropper.options
import com.students.qb365.R
import com.students.qb365.utils.DialogUtil
import com.students.qb365.databinding.ActivityEditProfileBinding
import com.students.qb365.ui.dashboard.MainViewModel
import com.students.qb365.ui.dashboard.model.Profile
import com.students.qb365.utils.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditProfileActivity : AppCompatActivity() {

    private var binding: ActivityEditProfileBinding? = null

    private val model: MainViewModel by viewModels()

    private var image = ""

    private lateinit var profile: Profile

    private val cropImage = registerForActivityResult(CropImageContract()) { result ->
        if (result.isSuccessful) {
            // use the returned uri
            val uriContent = result.uriContent
            val uriFilePath = result.getUriFilePath(this)

            image = uriContent?.toBase64(this)!!
            binding?.icProfile?.loadCircular(uriContent, R.drawable.ic_user_placeholder)

//            Log.e("base64", image)

        } else {
            // an error occurred
            val exception = result.error
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        profile = intent.getParcelableExtra("data")!!

        binding?.apply {

            icProfile.loadCircular(profile.data?.image!!, R.drawable.ic_user_placeholder)

            etName.setText(profile.data?.studName)
            etEmail.setText(profile.data?.studEmail)
            etSchoolName.setText(profile.data?.schoolName)
            etPhone.setText(profile.data?.studPhone)
            etAddressName.setText(profile.data?.studAddress)

            ivBack.setOnClickListener {
                onBackPressed()
            }

            ivEdit.setOnClickListener {
                cropImage.launch(
                    options {
                        setGuidelines(CropImageView.Guidelines.ON)
                    }
                )
            }

            btnUpdate.setOnClickListener {
                val name = etName.text.toString()
                val email = etEmail.text.toString()
                val schoolName = etSchoolName.text.toString()
                val address = etAddressName.text.toString()
                val phone = etPhone.text.toString()

                if (name.isEmpty()) {
                    errorToast("Enter Name")
                    return@setOnClickListener
                }

                if (email.isEmpty()) {
                    errorToast("Enter Email")
                    return@setOnClickListener
                }

                if (schoolName.isEmpty()) {
                    errorToast("Enter School Name")
                    return@setOnClickListener
                }

                if (address.isEmpty()) {
                    errorToast("Enter Address")
                    return@setOnClickListener
                }

                if (phone.isEmpty()) {
                    errorToast("Enter Phone Number")
                    return@setOnClickListener
                }

                if (!email.matches(Patterns.EMAIL_ADDRESS.toRegex())) {
                    errorToast("Enter Valid Email Address")
                    return@setOnClickListener
                }

                DialogUtil.showProgressDialog(this@EditProfileActivity)
                val boardId = SharedPrefs.getString(
                    this@EditProfileActivity,
                    SharedPrefs.BOARD_ID
                )

                model.editProfile(
                    email,
                    phone,
                    name,
                    image,
                    boardId!!,
                    schoolName,
                    address,
                    { baseResponse ->
                        DialogUtil.dismissProgressDialog()
                        successToast(baseResponse.message!!)
                        val intent = Intent()
                        setResult(RESULT_OK, intent)
                        finish()

                    },
                    { s ->
                        DialogUtil.dismissProgressDialog()
                    })

            }

        }

    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}