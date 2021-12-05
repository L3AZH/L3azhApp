package com.l3azh.l3azhapp.api.res

import com.google.gson.annotations.SerializedName

data class GetCTDTItemResData(
    @SerializedName("MAMONHOC")
    val codeSubject:String,
    @SerializedName("TENMONHOC")
    val nameSubject: String,
    @SerializedName("SOTINCHI")
    val numberOfCredits: Int
)