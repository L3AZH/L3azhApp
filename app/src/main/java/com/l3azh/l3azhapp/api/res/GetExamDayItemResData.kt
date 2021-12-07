package com.l3azh.l3azhapp.api.res

import com.google.gson.annotations.SerializedName
import java.util.*

data class GetExamDayItemResData(
    @SerializedName("MAMONHOC")
    val codeSubject: String,
    @SerializedName("TENMONHOC")
    val nameSubject:String,
    @SerializedName("NGAYTHI")
    val examDay: Date,
    @SerializedName("PHONGTHI")
    val examRoom: String,
    @SerializedName("SOTIETBATDAU")
    val sessionBegin: Int,
    @SerializedName("GIOBATDAU")
    val timeBegin: String,
    @SerializedName("SOPHUT")
    val minusNumber: Int
) {
}