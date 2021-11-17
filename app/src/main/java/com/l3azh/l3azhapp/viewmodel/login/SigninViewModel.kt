package com.l3azh.l3azhapp.viewmodel.login

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import com.l3azh.l3azhapp.App
import com.l3azh.l3azhapp.api.res.BaseRes
import com.l3azh.l3azhapp.api.res.ErrorResData
import com.l3azh.l3azhapp.repository.Repository
import com.l3azh.l3azhapp.utils.SharePreferenceHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import java.net.SocketTimeoutException
import java.util.*
import javax.inject.Inject

class SigninViewModel(application: Application) : AndroidViewModel(application) {

    @Inject
    lateinit var repository: Repository

    init {
        (application as App).appComponent!!.inject(this)
    }

    fun login(
        context: Context,
        username: String,
        password: String,
        role: String
    ): Deferred<Map<String, Any>> = CoroutineScope(Dispatchers.Default).async {
        var result: MutableMap<String, Any> = mutableMapOf()
        try{
            val response = repository.login(username, password, role)
            if (response.isSuccessful) {
                SharePreferenceHelper.saveToken(context, response.body()!!.data.token)
                SharePreferenceHelper.saveUsername(context, username)
                result.put("code", response.body()!!.code)
                result.put("message", response.body()!!.data.message)
                result.put("flag", response.body()!!.flag)
            }
            else{
                val errorRes = ErrorResData.convertErrorBodyToErrorRes(response.errorBody()!!)
                result.put("code", errorRes.code)
                result.put("message", errorRes.data.message)
                result.put("flag", errorRes.flag)
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