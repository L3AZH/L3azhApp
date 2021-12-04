package com.l3azh.l3azhapp.api.res

import com.google.gson.annotations.SerializedName
import com.l3azh.l3azhapp.api.model.ImageFromMySql
import java.util.*

data class GetHealthDeclarationItemResData(
    @SerializedName("MATOKHAI")
    val codeDeclaration:String,
    @SerializedName("TINHTRANGSUCKHOE")
    val healthStatus: String,
    @SerializedName("NOIDI")
    val startDestination: String,
    @SerializedName("NOIDEN")
    val endDestination: String,
    @SerializedName("NGAYGIO")
    val time: Long,
    @SerializedName("MAQR")
    val qrPic: ImageFromMySql,
    @SerializedName("SINHVIENMASINHVIEN")
    val codeStudent: String,
)