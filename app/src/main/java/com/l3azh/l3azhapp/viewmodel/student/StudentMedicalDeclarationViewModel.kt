package com.l3azh.l3azhapp.viewmodel.student

import android.app.Application
import android.graphics.Bitmap
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.l3azh.l3azhapp.App
import com.l3azh.l3azhapp.api.req.HealthDeclarationItemReqData
import com.l3azh.l3azhapp.api.res.ErrorResData
import com.l3azh.l3azhapp.api.res.GetHealthDeclarationItemResData
import com.l3azh.l3azhapp.repository.Repository
import kotlinx.coroutines.*
import java.io.ByteArrayOutputStream
import java.net.SocketTimeoutException
import java.util.*
import javax.inject.Inject
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.journeyapps.barcodescanner.BarcodeEncoder
import java.lang.Exception


class StudentMedicalDeclarationViewModel(application: Application): AndroidViewModel(application) {

    init {
        (application as App).appComponent!!.inject(this)
    }

    var listHealthDeclaration:MutableLiveData<List<GetHealthDeclarationItemResData>> =
        MutableLiveData()

    @Inject
    lateinit var repository: Repository

    fun getListHealthDeclaration(
        bearerToken:String,
        codeOfStudent:String
    ):Deferred<Map<String, Any>> = CoroutineScope(Dispatchers.IO).async {
        var result:MutableMap<String, Any> = mutableMapOf()
        try{
            val response = repository.getListHealthDeclaration(bearerToken, codeOfStudent)
            if(response.isSuccessful){
                listHealthDeclaration.postValue(response.body()!!.data)
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
        catch (timeoutEx:SocketTimeoutException){
            result.put("code", 408)
            result.put("message", timeoutEx.message!!.toString())
            result.put("flag", false)
        }
        result
    }

    fun createNewHealthDeclaration(
        bearerToken: String,
        healthStatus:String,
        startDestination:String,
        endDestination:String,
        codeOfStudent: String,
    ):Deferred<Map<String, Any>> = CoroutineScope(Dispatchers.IO).async{
        var result:MutableMap<String, Any> = mutableMapOf()
        try{
            val currentTime =  Date().time
            val data = HealthDeclarationItemReqData(
                healthStatus,
                startDestination,
                endDestination,
                currentTime,
                generateQrCode("" +
                        "$healthStatus-" +
                        "$startDestination-" +
                        "$endDestination-" +
                        "$currentTime-" +
                        "$codeOfStudent")!!,
                codeOfStudent
            )
            val response = repository.createNewHealthDeclaration(bearerToken, data)
            if(response.isSuccessful){
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
        catch (timeoutEx:SocketTimeoutException){
            result.put("code", 408)
            result.put("message", timeoutEx.message!!.toString())
            result.put("flag", false)
        }
        result
    }

    fun generateQrCode(data:String):ByteArray?{
        val multiFormatWriter = MultiFormatWriter()

        try {
            val bitMatrix = multiFormatWriter.encode(
                data,
                BarcodeFormat.QR_CODE,
                512,
                512
            )
            val barcodeEncoder = BarcodeEncoder()
            val bitmap: Bitmap = barcodeEncoder.createBitmap(bitMatrix)
            val stream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
            val imageByte = stream.toByteArray()
            return imageByte
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }
}