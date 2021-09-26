package com.l3azh.l3azhapp.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys

class EncryptSharePreferenceHelper {
    companion object{
        private val USER_DATA_LOGIN = "user_data_login"
        val DEFAULT_VALUE = "null"
        private val keyGenParameterSpec = MasterKeys.AES256_GCM_SPEC
        private var mainKeyAlias:String = ""
        private var sharedPreferences:SharedPreferences? = null

        fun getMainKeyAlias():String{
            if(mainKeyAlias.equals("",true)){
                mainKeyAlias = MasterKeys.getOrCreate(keyGenParameterSpec)
            }
            return mainKeyAlias;
        }

        fun getSharedPreferences(context: Context):SharedPreferences{
            if(sharedPreferences == null){
                sharedPreferences = EncryptedSharedPreferences.create(
                    USER_DATA_LOGIN,
                    getMainKeyAlias(),
                    context,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
                )
            }
            return sharedPreferences!!
        }

        fun saveDataToSharedPreferences(context: Context,key:String,value:String){
            getSharedPreferences(context).edit().apply {
                putString(key, value)
            }.apply()
        }

        fun readDataSharedPreference(context: Context,key:String):String{
            return getSharedPreferences(context).getString(key, DEFAULT_VALUE)!!
        }
    }

}