package com.kevinjanvier.mvvm.model


class Payload {
    var username:String?= null
    var password:String?=null
    var id:Int?= null
    var Basic:String?= null
    var role: List<Role>?= null
    val status: Status? = null
    val error: Throwable? = null


    constructor(username:String, password:String)

    constructor(status: Status, payload: Payload?, error:Throwable?)

    companion object {
        fun loading(): Payload {
            return Payload(Status.LOADING, null, null)
        }

        fun success(payload: Payload?): Payload {
            return Payload(Status.SUCCESS, payload, null)
        }


        fun error(error: Throwable?): Payload {
            return Payload(Status.ERROR, null, error)
        }
    }
}




data class Role(var id:Int, var name:String, var permissions:List<String>)

class PayloadResponse{


}

enum class Status{
    LOADING,
    SUCCESS,
    ERROR
}