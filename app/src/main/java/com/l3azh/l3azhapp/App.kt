package com.l3azh.l3azhapp

import android.app.Application
import com.l3azh.l3azhapp.di.AppComponent
import com.l3azh.l3azhapp.di.DaggerAppComponent

class App:Application() {

    var appComponent: AppComponent? = null

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().build()
    }

}