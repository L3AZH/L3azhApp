package com.l3azh.l3azhapp.api.res

import android.icu.text.SimpleDateFormat
import com.google.gson.annotations.SerializedName
import java.util.*

class GetSemesterSchoolYearWeekItemResData(
    @SerializedName("HOCKINAMHOCSOTUAN")
    val semesterSchoolYearWeek:String,
    @SerializedName("NGAYBATDAU")
    val startDate: Date,
    @SerializedName("NGAYKETTHUC")
    var endDate: Date
) {

    override fun toString(): String {
        val sdf = SimpleDateFormat("dd/MM/yyyy")
        return "${semesterSchoolYearWeek} ( ${sdf.format(startDate)} - ${sdf.format(endDate)} )"
    }
}