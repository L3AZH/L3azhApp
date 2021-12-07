package com.l3azh.l3azhapp.viewmodel.student

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.l3azh.l3azhapp.App
import com.l3azh.l3azhapp.api.res.ErrorResData
import com.l3azh.l3azhapp.api.res.GetLichHocItemResData
import com.l3azh.l3azhapp.api.res.GetSemesterSchoolYearWeekItemResData
import com.l3azh.l3azhapp.repository.Repository
import com.l3azh.l3azhapp.utils.Utils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import java.net.SocketTimeoutException
import javax.inject.Inject

class StudentTimeTableViewModel(application: Application): AndroidViewModel(application) {
    @Inject
    lateinit var repository: Repository
    var isFisrtTimeCreate:Boolean = false

    init {
        (application as App).appComponent!!.inject(this)
        isFisrtTimeCreate = true
    }

    var listWeekNumber: MutableLiveData<List<GetSemesterSchoolYearWeekItemResData>> = MutableLiveData()
    var listSubjectInWeek : MutableLiveData<List<GetLichHocItemResData>> = MutableLiveData()

    fun getListWeekNumber(
        bearerToken:String,
        semesterSchoolYear:String
    ): Deferred<Map<String, Any>> = CoroutineScope(Dispatchers.IO).async {
        var result:MutableMap<String, Any> = mutableMapOf()
        try{
            val response = repository.getListSemsterSchoolYearWeek(bearerToken, semesterSchoolYear)
            if(response.isSuccessful){
                listWeekNumber.postValue(response.body()!!.data)
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

    fun getListSubjectInWeek(
        bearerToken:String,
        semesterSchoolYearWeek:String,
        codeOfStudent: String
    ): Deferred<Map<String, Any>> = CoroutineScope(Dispatchers.IO).async {
        var result:MutableMap<String, Any> = mutableMapOf()
        try{
            val response = repository.getTimeTable(bearerToken, semesterSchoolYearWeek,codeOfStudent)
            if(response.isSuccessful){
                listSubjectInWeek.postValue(response.body()!!.data)
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

    fun setFlagAfterInitFirstTimeData(){
        isFisrtTimeCreate = false
    }
}