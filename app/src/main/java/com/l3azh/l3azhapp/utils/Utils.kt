package com.l3azh.l3azhapp.utils

import android.graphics.Color
import android.os.Build
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.*


class Utils {
    companion object {
        fun <T> logError(classType: T, message: String) {
            Log.e(classType!!::class.simpleName, "Error: $message")
        }
        fun <T> logDebug(classType: T, message: String){
            Log.d(classType!!::class.simpleName, "Debug: $message")
        }

        fun showSnackbarError(message: String, view: View){
            Snackbar.make(view, message, Snackbar.LENGTH_LONG).setBackgroundTint(Color.RED).show()
        }

        fun showSnackbarSuccess(message: String, view: View){
            Snackbar.make(view, message, Snackbar.LENGTH_LONG).setBackgroundTint(Color.GREEN).show()
        }

        @RequiresApi(Build.VERSION_CODES.N)
        fun convertTimeStampToStringDate(timeStamp:Long):String?{
            val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
            val dateData = Date(timeStamp)
            return sdf.format(dateData)
        }
    }
}