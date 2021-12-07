package com.l3azh.l3azhapp.api.res

import com.google.gson.annotations.SerializedName

data class GetLichHocItemResData(
    @SerializedName("MONHOC_MAMONHOC")
    val codeSubject: String,
    @SerializedName("SOTHUTRONGTUAN")
    val dayNumber:Int,
    @SerializedName("PHONGHOC")
    val classRoom: String,
    @SerializedName("TIETBATDAU")
    val sessionBegin: Int,
    @SerializedName("GIOBATDAU")
    val timeBegin: Int,
    @SerializedName("HOCKINAMHOCSOTUAN")
    val semesterSchoolYearWeek:String
)