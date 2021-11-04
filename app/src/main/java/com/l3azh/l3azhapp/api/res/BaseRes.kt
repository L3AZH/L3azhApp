package com.l3azh.l3azhapp.api.res

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

data class BaseRes<T>(
    @SerializedName("code")
    val code:Int,
    @SerializedName("flag")
    val flag:Boolean,
    @SerializedName("data")
    var data:T
)