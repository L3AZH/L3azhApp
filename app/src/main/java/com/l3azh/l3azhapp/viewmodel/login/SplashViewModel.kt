package com.l3azh.l3azhapp.viewmodel.login

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import com.l3azh.l3azhapp.App
import com.l3azh.l3azhapp.repository.Repository
import com.l3azh.l3azhapp.utils.SharePreferenceHelper
import javax.inject.Inject

class SplashViewModel(application: Application) : AndroidViewModel(application) {

    @Inject
    lateinit var repository: Repository

    init {
        (application as App).appComponent!!.inject(this)
    }

    fun checkExistToken(context: Context): Boolean {
        val token = SharePreferenceHelper.getToken(context)
        token ?: return false
        return true
    }
}