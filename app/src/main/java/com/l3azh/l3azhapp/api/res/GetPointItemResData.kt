package com.l3azh.l3azhapp.api.res

import com.google.gson.annotations.SerializedName

data class GetPointItemResData(
    @SerializedName("MAMONHOC")
    val codeSubject: String,
    @SerializedName("TENMONHOC")
    val nameSubject:String,
    @SerializedName("HEDIEMCC")
    val pointSystemCC:Int,
    @SerializedName("HEDIEMSE")
    val pointSystemSE:Int,
    @SerializedName("HEDIEMKT")
    val pointSystemKT:Int,
    @SerializedName("HEDIEMTH")
    val pointSystemTH:Int,
    @SerializedName("HEDIEMTHI")
    val pointSystemTHI:Int,
    @SerializedName("DIEMCC")
    val pointCC:Double,
    @SerializedName("DIEMSE")
    val pointSE:Double,
    @SerializedName("DIEMKT")
    val pointKT:Double,
    @SerializedName("DIEMTH")
    val pointTH:Double,
    @SerializedName("DIEMTHI")
    val pointTHI:Double,
) {
}