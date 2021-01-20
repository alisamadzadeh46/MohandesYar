package com.example.mohandesipezeshki.viewmodel

import android.app.Application
import android.view.View
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.mohandesipezeshki.model.ModelStatus
import com.example.mohandesipezeshki.model.ModelUserInfo
import com.example.mohandesipezeshki.repository.Api
import com.example.mohandesipezeshki.repository.HandelRequest
import com.example.mohandesipezeshki.repository.MainThered
import com.sdsmdg.tastytoast.TastyToast
import kotlinx.coroutines.Job

class ViewModelCheckPhone(val app:Application) : AndroidViewModel(app) {
    private lateinit var job: Job
    val mutableLiveData = MutableLiveData<ModelStatus>()
    var phone:String?=null
    fun CheckPhone(view: View) {
        when{
            phone.isNullOrEmpty() -> {
                TastyToast.makeText(
                    app.applicationContext,
                    "لطفا کد را وارد کنید",
                    TastyToast.LENGTH_LONG,
                    TastyToast.WARNING
                )
            }
            phone!!.length<11 -> {
                TastyToast.makeText(
                    app.applicationContext,
                    "شماره وارد شده معتبر نیست",
                    TastyToast.LENGTH_LONG,
                    TastyToast.WARNING
                )
            }
            phone!!.length>=12 -> {
                TastyToast.makeText(
                    app.applicationContext,
                    "شماره وارد شده معتبر نیست",
                    TastyToast.LENGTH_LONG,
                    TastyToast.WARNING
                )
            }
            else ->{
                job = MainThered.CoroutinesHandel({
                    HandelRequest.Request(Api.invoke().ChechPhone(phone!!))
                },
                    {

                        mutableLiveData.value = it
                    })
            }
        }

    }




    override fun onCleared() {
        if (::job.isInitialized) job.cancel()
        super.onCleared()
    }
}