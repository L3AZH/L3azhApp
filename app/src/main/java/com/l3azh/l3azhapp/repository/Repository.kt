package com.l3azh.l3azhapp.repository

import android.app.Application
import com.l3azh.l3azhapp.api.L3azhApi
import com.l3azh.l3azhapp.api.req.LoginReqData
import com.l3azh.l3azhapp.api.res.BaseRes
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
}