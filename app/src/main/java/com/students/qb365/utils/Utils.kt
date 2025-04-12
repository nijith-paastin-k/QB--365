package com.students.qb365.utils

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.os.Build
import android.text.InputType
import android.widget.EditText
import android.widget.ImageView
import androidx.core.content.ContextCompat.startActivity
import androidx.core.content.res.ResourcesCompat
import com.students.qb365.R
import com.students.qb365.ui.auth.model.SignUp


class Utils {

    companion object {

        var phoneNumber = ""

        var signUpData: SignUp = SignUp()

        fun isInternetAvailable(context: Context): Boolean {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val nw = connectivityManager.activeNetwork ?: return false
                val actNw = connectivityManager.getNetworkCapabilities(nw)
                actNw != null && (actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) || actNw.hasTransport(
                    NetworkCapabilities.TRANSPORT_CELLULAR
                ) || actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) || actNw.hasTransport(
                    NetworkCapabilities.TRANSPORT_BLUETOOTH
                ))
            } else {
                val nwInfo = connectivityManager.activeNetworkInfo
                nwInfo != null && nwInfo.isConnected
            }
        }


        fun eyeToggle(context: Context?, editText: EditText, eyeToggle: ImageView) {
            val typeface = ResourcesCompat.getFont(context!!, R.font.poppins_regular)
            if (eyeToggle.isSelected) {
                eyeToggle.isSelected = false
                editText.inputType = InputType.TYPE_CLASS_TEXT
                editText.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            } else {
                eyeToggle.isSelected = true
                editText.inputType = InputType.TYPE_CLASS_TEXT
            }
            editText.typeface = typeface
            editText.setSelection(editText.text.length)
        }

        fun openUrl(context: Context, url: String) {
            try {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                context.startActivity(browserIntent)
            } catch (e: Exception) {
            }
        }


    }


}