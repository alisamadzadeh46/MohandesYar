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

class ViewModelChangePassword(val app:Application) : AndroidViewModel(app) {
    private lateinit var job: Job
    val mutableLiveData = MutableLiveData<ModelStatus>()
    var password:String?=null
    var phone:String?=null
    fun CheckCode(view: View) {
        when{
            password.isNullOrEmpty() -> {
                TastyToast.makeText(
                    app.applicationContext,
                    "لطفا پسورد را وارد کنید",
                    TastyToast.LENGTH_LONG,
                    TastyToast.WARNING
                )
            }
            else ->{
                job = MainThered.CoroutinesHandel({
                    HandelRequest.Request(Api.invoke().ChangePassword(phone!!,password!!))
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