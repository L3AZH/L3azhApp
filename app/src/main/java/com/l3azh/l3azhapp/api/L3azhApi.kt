package com.l3azh.l3azhapp.api

import com.l3azh.l3azhapp.api.req.LoginReqData
import com.l3azh.l3azhapp.api.res.BaseRes
import com.l3azh.l3azhapp.api.res.LoginResData
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST


interface L3azhApi {
    @POST("auth/login")
    suspend fun login(
        @Header("Authorization") basicToken:String,
        @Body request:LoginReqData
    ):Response<BaseRes<LoginResData>>
}