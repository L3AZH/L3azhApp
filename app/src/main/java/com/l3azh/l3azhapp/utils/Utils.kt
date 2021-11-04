package com.l3azh.l3azhapp.utils

import android.graphics.Color
import android.util.Log
import android.view.View
import com.google.android.material.snackbar.Snackbar


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
    }
}