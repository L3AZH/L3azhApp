package com.l3azh.l3azhapp.api

import com.l3azh.l3azhapp.api.req.LoginReqData
import com.l3azh.l3azhapp.api.res.BaseRes
import com.l3azh.l3azhapp.api.res.GetInfoTeacherResData
import com.l3azh.l3azhapp.api.res.GetInfoStudentResData
import com.l3azh.l3azhapp.api.res.LoginResData
import retrofit2.Response
import retrofit2.http.*


interface L3azhApi {

    @POST("auth/login")
    suspend fun login(
        @Header("Authorization") basicToken: String,
        @Body request:LoginReqData
    ):Response<BaseRes<LoginResData>>

    @GET("sinhvien/get-info")
    suspend fun getStudentInfo(
        @Header("Authorization") bearerToken: String,
        @Query("masv") codeOfStudent: String
    ): Response<BaseRes<GetInfoStudentResData>>

    @GET("giangvien/get-info")
    suspend fun getTeacherInfo(
        @Header("Authorization") bearerToken: String,
        @Query("magv") codeOfTeacher: String
    ): Response<BaseRes<GetInfoTeacherResData>>
}