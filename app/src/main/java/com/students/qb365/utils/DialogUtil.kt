package com.students.qb365.utils

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import com.pinecode.twinglar.utils.ProgressDialogFragment

object DialogUtil {
    private const val TAG_FRAGMENT = "Dialog"
    private var mDialogFragment: DialogFragment? = null
    fun showProgressDialog(activity: Activity?) {
        val act = activity as AppCompatActivity
        if (activity == null || activity.isFinishing) {
            return
        }
        if (mDialogFragment != null && mDialogFragment!!.isVisible) {
            return
        }
        val mPrevious = mDialogFragment
        mDialogFragment = ProgressDialogFragment()
        (mDialogFragment as ProgressDialogFragment).setCancelable(false)
        if (mPrevious != null) {
            act.supportFragmentManager.beginTransaction().remove(mPrevious).commitAllowingStateLoss()
        }
        act.supportFragmentManager.beginTransaction()
            .add(mDialogFragment as ProgressDialogFragment, TAG_FRAGMENT)
            .commitAllowingStateLoss()
    }

    fun dismissProgressDialog() {
        if (mDialogFragment != null) {
            mDialogFragment!!.dismissAllowingStateLoss()
            mDialogFragment = null
        }
    }
}