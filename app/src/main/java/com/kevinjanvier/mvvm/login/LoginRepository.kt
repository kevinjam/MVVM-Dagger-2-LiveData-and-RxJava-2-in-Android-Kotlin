package com.kevinjanvier.mvvm.login

import com.kevinjanvier.mvvm.di.Helper
import com.kevinjanvier.mvvm.model.Payload
import com.kevinjanvier.mvvm.utils.ApiCallInterface
import io.reactivex.Observable

class LoginRepository{

    private val apiservice :ApiCallInterface

    init {
        apiservice = Helper.createService(ApiCallInterface::class.java)
    }

    fun executeLogin(login:Payload): Observable<Payload> {
        return apiservice.login(login)
    }

}