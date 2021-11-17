package com.l3azh.l3azhapp.api.model

import com.google.gson.annotations.SerializedName

class ImageFromMySql(
    @SerializedName("type")
    val type: String,
    @SerializedName("data")
    val data: ByteArray
)