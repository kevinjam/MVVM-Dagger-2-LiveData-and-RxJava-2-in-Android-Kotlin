package com.kevinjanvier.mvvm.di
import android.text.TextUtils
import android.util.Log

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.kevinjanvier.mvvm.BuildConfig
import com.kevinjanvier.mvvm.login.LoginRepository
import com.kevinjanvier.mvvm.utils.AuthenticationInterceptor
import com.kevinjanvier.mvvm.utils.BASE_URL
import com.kevinjanvier.mvvm.utils.ViewModelFactory
import dagger.Module
import dagger.Provides

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
object Helper {

    @Provides
    @Singleton
    fun toStringGson() = GsonBuilder().setPrettyPrinting().create()

    @Provides
    @Singleton
    fun gson() = GsonBuilder()
        .setLenient()
        .create()

    /**
     * set timeout
     */

    @Provides
    @Singleton
    fun okHttpClient() = OkHttpClient.Builder()
        .readTimeout(60, TimeUnit.SECONDS)
        .connectTimeout(60, TimeUnit.SECONDS)
        .build()


    /**
     * Retrofit Calls
     */

    private val httpClient = OkHttpClient.Builder()

    private val builder = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient())
        .addConverterFactory(GsonConverterFactory.create(gson()))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

    private var retrofit = builder.build()


    fun <S> createService(serviceClass: Class<S>): S {

          return createService(serviceClass, "")
    }

    fun <S> createService(
        serviceClass: Class<S>, authToken: String?
    ): S {
        if (!TextUtils.isEmpty(authToken)) {
            val interceptor = AuthenticationInterceptor(authToken!!)

            if (!httpClient.interceptors().contains(interceptor)) {
                httpClient.addInterceptor(interceptor)
                builder.client(httpClient.build())
                retrofit = builder.build()
            }
        }

        return retrofit.create(serviceClass)
    }


    /**
     * @msg:message pass by the logs
     * this Only on Debug mode
     * Removed to Production
     */
    fun log(msg:String){
        if(BuildConfig.DEBUG){
            Log.e(javaClass.simpleName, msg)
        }else{
        }

    }

//    fun getViewModelFactory(repository: LoginRepository): ViewModelFactory {
//        return ViewModelFactory(repository)
//    }



}
