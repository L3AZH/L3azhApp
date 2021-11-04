package com.l3azh.l3azhapp.api.req

import com.google.gson.annotations.SerializedName

data class LoginReqData(
    @SerializedName("ma")
    val ma:String,
    @SerializedName("matkhau")
    val matKhau:String,
    @SerializedName("role")
    var role:String
)