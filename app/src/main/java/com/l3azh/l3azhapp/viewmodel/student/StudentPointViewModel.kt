package com.l3azh.l3azhapp.viewmodel.student

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.l3azh.l3azhapp.App
import com.l3azh.l3azhapp.api.res.ErrorResData
import com.l3azh.l3azhapp.api.res.GetPointItemResData
import com.l3azh.l3azhapp.repository.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import java.net.SocketTimeoutException
import javax.inject.Inject

class StudentPointViewModel(application: Application):AndroidViewModel(application) {
    @Inject
    lateinit var repository: Repository

    init {
        (application as App).appComponent!!.inject(this)
    }

    var listPoint: MutableLiveData<List<GetPointItemResData>> = MutableLiveData()

    fun getListPoint(
        bearerToken:String,
        semesterSchoolYear:String,
        codeOfStudent: String
    ): Deferred<Map<String, Any>> = CoroutineScope(Dispatchers.IO).async {
        var result:MutableMap<String, Any> = mutableMapOf()
        try{
            val response = repository.getListPoint(bearerToken, semesterSchoolYear,codeOfStudent)
            if(response.isSuccessful){
                listPoint.postValue(response.body()!!.data)
                result["code"] = response.body()!!.code
                result["flag"] = response.body()!!.flag
            }
            else{
                val error = ErrorResData.convertErrorBodyToErrorRes(response.errorBody()!!)
                result["code"] = error.code
                result["flag"] = error.flag
                result["message"] = error.data.message
            }
        }
        catch (timeoutEx: SocketTimeoutException){
            result.put("code", 408)
            result.put("message", timeoutEx.message!!.toString())
            result.put("flag", false)
        }
        result
    }

}