package com.students.qb365.utils

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.text.Html
import android.text.Spanned
import android.util.Base64
import android.view.View
import android.view.View.*
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.students.qb365.R
import com.students.qb365.ui.auth.LoginActivity
import es.dmoral.toasty.Toasty
import java.io.ByteArrayOutputStream


fun AppCompatActivity.openActivity(activity: Class<*>?) {
    val intent = Intent(this, activity)
    startActivity(intent)
}

fun AppCompatActivity.infoToast(msg: String) {
    Toasty.info(this, msg, Toast.LENGTH_SHORT, true).show();
}


fun AppCompatActivity.successToast(msg: String) {
    Toasty.success(this, msg, Toast.LENGTH_SHORT, true).show();
}

fun AppCompatActivity.errorToast(msg: String) {
    Toasty.error(this, msg, Toast.LENGTH_SHORT, true).show();
}

fun Fragment.successToast(msg: String) {
    Toasty.success(requireContext(), msg, Toast.LENGTH_SHORT, true).show();
}

fun ImageView.load(url: String?) {
    Glide.with(this.context)
        .load(url)
        .error(R.drawable.main_logo)
        .into(this)
}

fun ImageView.loadCircular(url: String, image: Int) {
    Glide.with(this.context)
        .load(url)
        .circleCrop()
        .error(image)
        .into(this)
}

fun ImageView.loadCircular(url: Uri, image: Int) {
    Glide.with(this.context)
        .load(url)
        .circleCrop()
        .error(image)
        .into(this)
}

fun ImageView.load(url: Uri) {
    Glide.with(this.context)
        .load(url)
        .error(R.drawable.main_logo)
        .into(this)
}

fun View.show() {
    this.visibility = VISIBLE
}

fun View.invisible() {
    this.visibility = INVISIBLE
}

fun View.gone() {
    this.visibility = GONE
}

fun Uri.toBase64(context: Context): String {
    val imageStream = context.contentResolver.openInputStream(this)
    val selectedImage = BitmapFactory.decodeStream(imageStream)
    val baos = ByteArrayOutputStream()
    selectedImage.compress(Bitmap.CompressFormat.JPEG, 100, baos)
    val bArr = baos.toByteArray();
    return Base64.encodeToString(bArr, Base64.DEFAULT)
}

//fun String?.toHtml() : CharSequence {
//    return Html.fromHtml(this).trim()
//}