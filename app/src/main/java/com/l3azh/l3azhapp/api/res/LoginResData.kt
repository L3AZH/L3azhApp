package com.l3azh.l3azhapp.api.res

import com.google.gson.annotations.SerializedName

class LoginResData(
    @SerializedName("message")
    val message:String,
    @SerializedName("token")
    val token:String
)