package com.l3azh.l3azhapp.viewmodel.teacher

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.l3azh.l3azhapp.App
import com.l3azh.l3azhapp.repository.Repository
import javax.inject.Inject

class TeacherInfoViewModel(application: Application): AndroidViewModel(application) {

    @Inject
    lateinit var repository: Repository

    init {
        (application as App).appComponent!!.inject(this)
    }
}