package com.example.mohandesipezeshki.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mohandesipezeshki.model.ModelDaftarche
import com.example.mohandesipezeshki.model.ModelEmDetails
import com.example.mohandesipezeshki.model.ModelEmployee
import com.example.mohandesipezeshki.repository.Api
import com.example.mohandesipezeshki.repository.HandelRequest
import com.example.mohandesipezeshki.repository.MainThered
import kotlinx.coroutines.Job

class ViewModelEmployeeDetails : ViewModel() {
    lateinit var job: Job
    val mutableLiveData = MutableLiveData<ModelEmDetails>()
    fun ListDetails(id:String) {
        job = MainThered.CoroutinesHandel({
            HandelRequest.Request(Api.invoke().PostDetials(id))
        },
            {

                mutableLiveData.value = it
            })
    }

    override fun onCleared() {
        if (::job.isInitialized) job.cancel()
        super.onCleared()
    }
}