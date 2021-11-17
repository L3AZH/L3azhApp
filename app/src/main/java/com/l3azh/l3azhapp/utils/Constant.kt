package com.l3azh.l3azhapp.utils

import android.content.Context
import android.util.Base64
import android.util.Log

class Constant {
    companion object{
        val TAG = Constant.javaClass.simpleName
        const val BASE_URL = "http://192.168.1.10:7777/l3azh/api/"
        const val BASIC_AUTH_USERNAME = "LAMHATUANANH"
        const val BASIC_AUTH_PASSWORD = "N17DCCN004"

        fun getBasicAuthToken():String{
            val basicToken = "$BASIC_AUTH_USERNAME:$BASIC_AUTH_PASSWORD"
            val byteBasicToken = basicToken.toByteArray()
            val tokenEncodeBase64 = Base64.encodeToString(byteBasicToken,Base64.NO_WRAP)
            Log.e(TAG, "getBasicAuthToken: Basic $tokenEncodeBase64" )
            return "Basic $tokenEncodeBase64"
        }

    }
}