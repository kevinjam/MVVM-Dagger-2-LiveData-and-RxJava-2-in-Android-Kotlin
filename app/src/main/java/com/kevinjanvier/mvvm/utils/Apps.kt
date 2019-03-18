package com.kevinjanvier.mvvm.utils

import android.app.Application
import android.content.Context
import com.kevinjanvier.mvvm.di.AppComponent
import com.kevinjanvier.mvvm.di.AppModule
import com.kevinjanvier.mvvm.di.DaggerAppComponent
import com.kevinjanvier.mvvm.di.Helper

class Apps: Application() {

     var appComponent: AppComponent? = null
    var context:Context?= null

    override fun onCreate() {
        super.onCreate()
        context = this
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this))
            .helper(Helper).build()
    }


    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
    }
}