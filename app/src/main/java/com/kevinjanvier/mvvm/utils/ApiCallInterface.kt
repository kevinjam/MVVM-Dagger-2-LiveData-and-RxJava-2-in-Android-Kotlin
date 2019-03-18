package com.kevinjanvier.mvvm.utils

import com.kevinjanvier.mvvm.model.Payload
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiCallInterface {
    @POST(LOGIN)
     fun login(@Body login:Payload): Observable<Payload>

}