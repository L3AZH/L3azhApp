package com.l3azh.l3azhapp.api.res

import com.google.gson.annotations.SerializedName
import com.l3azh.l3azhapp.api.model.ImageFromMySql
import java.util.*

data class GetInfoStudentResData(
    @SerializedName("MASINHVIEN")
    val code: String,
    @SerializedName("HOTEN")
    val name: String,
    @SerializedName("NGAYSINH")
    val birthDay: Date,
    @SerializedName("PHAI")
    val sex: String,
    @SerializedName("NOISINH")
    val placeOfBirth: String,
    @SerializedName("EMAIL")
    val email: String,
    @SerializedName("ANHDAIDIEN")
    val avatar: ImageFromMySql?
)