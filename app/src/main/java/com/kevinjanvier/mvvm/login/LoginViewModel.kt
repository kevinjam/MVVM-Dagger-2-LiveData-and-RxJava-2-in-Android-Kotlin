package com.kevinjanvier.mvvm.login

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.kevinjanvier.mvvm.model.Payload
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class LoginViewModel:ViewModel() {

    private var repository:LoginRepository?= null
    private var disposable = CompositeDisposable()
    private var responseLivedata:MutableLiveData<Payload> = MutableLiveData()

    fun LoginViewModel(repository: LoginRepository) {
        this.repository = repository
    }
    fun loginResponse(): MutableLiveData<Payload> {
        return responseLivedata
    }


    fun hitlogin(login:Payload){
        disposable.add(repository!!.executeLogin(login)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { d -> responseLivedata.setValue(Payload.loading()) }
            .subscribe(
                { result -> responseLivedata.setValue(Payload.success(result)) },
                { throwable -> responseLivedata.setValue(Payload.error(throwable)) }
            ))

    }

    override fun onCleared() {
        disposable.clear()
    }
}