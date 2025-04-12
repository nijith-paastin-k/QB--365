package com.students.qb365.app

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class QBApp : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
        try {
        } catch (e: Exception) {
        }
    }

    fun setLogcat(msg: String?) {
        Log.d(TAG, msg!!)
    }

    companion object {
        private const val TAG = "MyApplication"

        @get:Synchronized
        var instance: QBApp? = null
            private set
    }
}