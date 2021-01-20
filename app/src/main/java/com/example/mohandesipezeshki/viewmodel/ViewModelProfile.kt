package com.example.mohandesipezeshki.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mohandesipezeshki.model.ModelUserInfo
import com.example.mohandesipezeshki.repository.Api
import com.example.mohandesipezeshki.repository.HandelRequest
import com.example.mohandesipezeshki.repository.MainThered
import kotlinx.coroutines.Job

class ViewModelProfile : ViewModel() {
    private lateinit var job: Job
    val mutableuserinfo = MutableLiveData<List<ModelUserInfo>>()
    fun Profile(token: String) {
        job = MainThered.CoroutinesHandel({
            HandelRequest.Request(Api.invoke().Profile(token))
        },
            {

                mutableuserinfo.value = it
            })


    }

    override fun onCleared() {
        if (::job.isInitialized) job.cancel()
        super.onCleared()
    }
}