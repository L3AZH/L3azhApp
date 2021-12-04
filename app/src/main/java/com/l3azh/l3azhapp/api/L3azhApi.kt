package com.l3azh.l3azhapp.api

import com.l3azh.l3azhapp.api.req.HealthDeclarationItemReqData
import com.l3azh.l3azhapp.api.req.LoginReqData
import com.l3azh.l3azhapp.api.res.*
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

    @GET("tokhai/get-to-khai")
    suspend fun getListHealthDeclaration(
        @Header("Authorization") bearerToken: String,
        @Query("masv") codeOfStudent: String
    ):Response<BaseRes<List<GetHealthDeclarationItemResData>>>

    @POST("tokhai/create-to-khai")
    suspend fun createNewHealthDeclaration(
        @Header("Authorization") bearerToken: String,
        @Body healthDeclarationItemRes: HealthDeclarationItemReqData
    ):Response<BaseRes<CreateNewHealthDeclarationResData>>
}