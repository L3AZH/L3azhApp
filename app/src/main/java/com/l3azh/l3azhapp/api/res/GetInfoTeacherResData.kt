package com.l3azh.l3azhapp.api.res

import com.google.gson.annotations.SerializedName
import com.l3azh.l3azhapp.api.model.ImageFromMySql
import java.util.*

data class GetInfoTeacherResData(
    @SerializedName("MAGIANGVIEN")
    val code: String,
    @SerializedName("HOTEN")
    val name: String,
    @SerializedName("NGAYSINH")
    val birth: Date,
    @SerializedName("GIOITINH")
    val sex: String,
    @SerializedName("TRINHDO")
    val degree: String,
    @SerializedName("TRANGTHAILAMVIEC")
    val workStatus: String,
    @SerializedName("ANHDAIDIEN")
    val avatar: ImageFromMySql
)