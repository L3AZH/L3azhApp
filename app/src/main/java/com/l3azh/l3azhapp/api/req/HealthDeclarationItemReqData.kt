package com.l3azh.l3azhapp.api.req

import com.google.gson.annotations.SerializedName
import com.l3azh.l3azhapp.api.model.ImageFromMySql
import java.util.*

data class HealthDeclarationItemReqData(
    @SerializedName("tinhtrangsuckhoe")
    val healthStatus: String,
    @SerializedName("noidi")
    val startDestination: String,
    @SerializedName("noiden")
    val endDestination: String,
    @SerializedName("ngaygio")
    val time: Long,
    @SerializedName("maqr")
    val qrPic: ByteArray,
    @SerializedName("masinhvien")
    val codeStudent: String,
)