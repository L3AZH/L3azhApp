package com.l3azh.l3azhapp.api.res

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import okhttp3.ResponseBody


import com.google.gson.reflect.TypeToken


import java.lang.reflect.Type


data class ErrorResData(
    @SerializedName("message")
    val message:String
){
    companion object{
        fun convertErrorBodyToErrorRes(errorBody: ResponseBody):BaseRes<ErrorResData>{
            val gson = Gson()
            val type:Type = object : TypeToken<BaseRes<ErrorResData>>(){}.type
            return gson.fromJson(errorBody.string(), type)
        }
    }
}