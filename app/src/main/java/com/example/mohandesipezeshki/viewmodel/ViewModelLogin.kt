package com.example.mohandesipezeshki.viewmodel

import android.app.Application
import android.view.View
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.mohandesipezeshki.model.ModelStatus
import com.example.mohandesipezeshki.repository.Api
import com.example.mohandesipezeshki.repository.HandelRequest
import com.example.mohandesipezeshki.repository.MainThered
import com.sdsmdg.tastytoast.TastyToast
import kotlinx.coroutines.Job

class ViewModelLogin(val app:Application) : AndroidViewModel(app) {
    lateinit var job: Job
    val mutableLiveData = MutableLiveData<ModelStatus>()
    val GetRegister = MutableLiveData<Boolean>()
    val forgetpassword = MutableLiveData<Boolean>()
    var phone:String?=null
    var password:String?=null
    fun Enter(view: View) {
        when{
            phone.isNullOrEmpty() -> {
                TastyToast.makeText(
                    app.applicationContext,
                    "لطفا شماره را وارد کنید",
                    TastyToast.LENGTH_LONG,
                    TastyToast.WARNING
                )
            }
            phone!!.length < 11 ->{
                TastyToast.makeText(
                    app.applicationContext,
                    "لطفا شماره معتبر  وارد کنید",
                    TastyToast.LENGTH_LONG,
                    TastyToast.WARNING
                )
            }
            phone!!.length >=12 ->{
                TastyToast.makeText(
                    app.applicationContext,
                    "لطفا شماره معتبر  وارد کنید",
                    TastyToast.LENGTH_LONG,
                    TastyToast.WARNING
                )
            }
            password.isNullOrEmpty() -> {
                TastyToast.makeText(
                    app.applicationContext,
                    "لطفا رمز عبور را وارد کنید",
                    TastyToast.LENGTH_LONG,
                    TastyToast.WARNING
                )
            }
            else ->{
                job = MainThered.CoroutinesHandel({
                    HandelRequest.Request(Api.invoke().Login(phone!!,password!!))
                },
                    {

                        mutableLiveData.value = it
                    })
            }
        }

    }
    fun IntentEnter(view: View) {
        GetRegister.value=true
    }
    fun ForgetPassword(view: View) {
        forgetpassword.value=true
    }
    override fun onCleared() {
        if (::job.isInitialized) job.cancel()
        super.onCleared()
    }
}