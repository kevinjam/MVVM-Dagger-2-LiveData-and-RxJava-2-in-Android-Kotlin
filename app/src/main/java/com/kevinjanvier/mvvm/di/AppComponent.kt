package com.kevinjanvier.mvvm.di

import com.kevinjanvier.mvvm.login.LoginActivity
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class, Helper::class])
@Singleton
interface AppComponent {

    fun doInjection(login:LoginActivity)
}