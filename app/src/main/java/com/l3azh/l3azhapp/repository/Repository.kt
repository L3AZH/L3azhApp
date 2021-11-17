package com.l3azh.l3azhapp.repository

import com.l3azh.l3azhapp.api.L3azhApi
import com.l3azh.l3azhapp.api.req.LoginReqData
import com.l3azh.l3azhapp.api.res.BaseRes
import com.l3azh.l3azhapp.api.res.GetInfoStudentResData
import com.l3azh.l3azhapp.api.res.GetInfoTeacherResData
import com.l3azh.l3azhapp.api.res.LoginResData
import com.l3azh.l3azhapp.utils.Constant
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(private val api: L3azhApi) {

    suspend fun login(
        username: String,
        password: String,
        role: String
    ): Response<BaseRes<LoginResData>> =
        api.login(Constant.getBasicAuthToken(), LoginReqData(username, password, role))

    suspend fun getInfoSinhVien(
        bearerToken: String,
        codeOfStudent: String
    ): Response<BaseRes<GetInfoStudentResData>> =
        api.getStudentInfo("Bearer $bearerToken", codeOfStudent)

    suspend fun getInfoTeacher(
        bearerToken: String,
        codeOfTeacher: String
    ): Response<BaseRes<GetInfoTeacherResData>> =
        api.getTeacherInfo("Bearer $bearerToken", codeOfTeacher)
}