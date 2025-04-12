package com.students.qb365.ui.auth.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import com.students.qb365.R
import com.students.qb365.databinding.ActivitySignUpBinding
import com.students.qb365.ui.auth.model.SignUp
import com.students.qb365.ui.auth.viewmodel.AuthViewModel
import com.students.qb365.utils.Utils
import com.students.qb365.utils.errorToast
import com.students.qb365.utils.openActivity
import dagger.hilt.android.AndroidEntryPoint
import java.util.regex.Pattern


@AndroidEntryPoint
class SignUpActivity : AppCompatActivity() {

    private var binding: ActivitySignUpBinding? = null

    private val model: AuthViewModel by viewModels()

    private var stateId = ""
    private var cityId = ""
    private var statePos = 0
    private var cityPos = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding?.root)


        binding?.apply {

            ivOldEye.setOnClickListener {
                Utils.eyeToggle(this@SignUpActivity, etPassword, ivOldEye)
            }

            model.getState({ state ->

                val list = ArrayList<String>()
                list.add("Select State")
                for (stateData in state.state) {
                    list.add(stateData.name!!)
                }
                val adapter = ArrayAdapter(
                    this@SignUpActivity, R.layout.simple_spinner_item, list
                )
                adapter.setDropDownViewResource(
                    R.layout.simple_spinner_dropdown_item
                )
                spnState.adapter = adapter
                spnState.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>?, view: View?, position: Int, id: Long
                    ) {
                        statePos = position

                        if (position != 0) {
                            stateId = state.state[position - 1].id!!.toString()
                            getCity(stateId)
                        }
//                            category = list[position]
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {
                    }


                }

            }, { s ->


            })


            btnContinue.setOnClickListener {
                val name = etName.text.toString().trim()
                val email = etEmail.text.toString().trim()
                val password = etPassword.text.toString()
                val refCode = etReferralCode.text.toString()
                val refCodeStaff = etReferralCodeStaff.text.toString()
                val school = etSchool.text.toString()
                val address = etAddress.text.toString()


                if (name.isEmpty()) {
                    errorToast("Enter Name")
                    return@setOnClickListener
                }

                if (email.isEmpty()) {
                    errorToast("Enter Email")
                    return@setOnClickListener
                }

                if (password.isEmpty()) {
                    errorToast("Enter Password")
                    return@setOnClickListener
                }

                if (school.isEmpty()) {
                    errorToast("Enter school")
                    return@setOnClickListener
                }

                if (address.isEmpty()) {
                    errorToast("Enter Address")
                    return@setOnClickListener
                }

                if (!email.matches(Patterns.EMAIL_ADDRESS.toRegex())) {
                    errorToast("Enter Valid Email Address")
                    return@setOnClickListener
                }

                if (statePos == 0) {
                    errorToast("Select State")
                    return@setOnClickListener
                }

                if (cityPos == 0) {
                    errorToast("Select City")
                    return@setOnClickListener
                }


                val signUp = SignUp()
                signUp.name = name
                signUp.cityId = cityId
                signUp.address = address
                signUp.email = email
                signUp.password = password
                signUp.schoolName = school

//                if (etReferralCode.text.toString().isNotEmpty()){
                signUp.refCodeStd = refCode
//                }

//                if (etReferralCodeStaff.text.toString().isNotEmpty()){
                signUp.refCodeStaff = refCodeStaff
//                }

                signUp.stateID = stateId
                signUp.cityId = cityId

                Utils.signUpData = signUp

                openActivity(BoardActivity::class.java)

            }

            ivBack.setOnClickListener {
                onBackPressed()
            }

        }

    }

    private fun getCity(stateID: String) {
        model.getCity(stateID, { city ->

            val list = ArrayList<String>()
            list.add("Select City")
            for (stateData in city.city) {
                list.add(stateData.name!!)
            }
            val adapter = ArrayAdapter(
                this@SignUpActivity, R.layout.simple_spinner_item, list
            )
            adapter.setDropDownViewResource(
                R.layout.simple_spinner_dropdown_item
            )
            binding?.spnCity?.adapter = adapter
            binding?.spnCity?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?, view: View?, position: Int, id: Long
                ) {
                    cityPos = position
                    cityId = city.city[position].id!!
//                            category = list[position]
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }
            }

        }, { s ->

        })
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}