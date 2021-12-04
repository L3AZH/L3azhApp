package com.l3azh.l3azhapp.api.res

import com.google.gson.annotations.SerializedName

class CreateNewHealthDeclarationResData(
    @SerializedName("message")
    val message:String,
    @SerializedName("newObject")
    val newObject: GetHealthDeclarationItemResData
)