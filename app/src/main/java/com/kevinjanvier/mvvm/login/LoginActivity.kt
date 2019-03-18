package com.kevinjanvier.mvvm.login

import android.app.ProgressDialog
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.gson.JsonElement
import com.kevinjanvier.mvvm.R
import com.kevinjanvier.mvvm.model.Payload
import com.kevinjanvier.mvvm.model.Status
import com.kevinjanvier.mvvm.utils.Apps
import com.kevinjanvier.mvvm.utils.ViewModelFactory
import javax.inject.Inject

class LoginActivity : AppCompatActivity() {

    @Inject
     var viewModelFactory: ViewModelFactory? = null

    var viewmodel:LoginViewModel?= null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        (application as Apps).appComponent!!.doInjection(this)

        viewmodel = ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel::class.java)
        viewmodel!!.loginResponse().observe(this, Observer<Payload> { this.consumeResponse(it!!) })

    }

    /*
     * method to handle response
     * */
    private fun consumeResponse(apiResponse: Payload) {

        when (apiResponse.status) {

            Status.LOADING -> println("😤load progress Bar")


            Status.SUCCESS -> {
                renderSuccessResponse(apiResponse)
            }

            Status.ERROR -> {
                println("👹 Something went wrong please Try again Later")
            }

            else -> {
            }
        }
    }

    private fun renderSuccessResponse(response: Payload) {
        //make a check

        if (response.code ="100") {
            Log.d("response=", response.toString())
        } else {
            println("👹 Something went wrong please Try again Later")
        }
    }
}
