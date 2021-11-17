package com.l3azh.l3azhapp.viewmodel.student

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.l3azh.l3azhapp.App
import com.l3azh.l3azhapp.api.res.ErrorResData
import com.l3azh.l3azhapp.repository.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import java.net.SocketTimeoutException
import javax.inject.Inject

class StudentInfoViewModel(application: Application) : AndroidViewModel(application) {

    @Inject
    lateinit var repository: Repository

    init {
        (application as App).appComponent!!.inject(this)
    }

    suspend fun getDataInfoStudent(
        bearerToken: String,
        codeOfSudent: String
    ): Deferred<Map<String, Any>> = CoroutineScope(Dispatchers.IO).async {
        var result: MutableMap<String, Any> = mutableMapOf()
        try {
            val response = repository.getInfoSinhVien(bearerToken, codeOfSudent)
            if (response.isSuccessful) {
                result["code"] = response.body()!!.code
                result["flag"] = response.body()!!.flag
                result["data"] = response.body()!!.data
            } else {
                val errRes = ErrorResData.convertErrorBodyToErrorRes(response.errorBody()!!)
                result["code"] = errRes.code
                result["flag"] = errRes.flag
                result["data"] = errRes.data
            }
        } catch (timeoutEx: SocketTimeoutException) {
            result.put("code", 408)
            result.put("message", timeoutEx.message!!.toString())
            result.put("flag", false)
        }
        result
    }

}