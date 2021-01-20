package com.example.mohandesipezeshki.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mohandesipezeshki.model.ModelDaftarche
import com.example.mohandesipezeshki.model.ModelEmployee
import com.example.mohandesipezeshki.repository.Api
import com.example.mohandesipezeshki.repository.HandelRequest
import com.example.mohandesipezeshki.repository.MainThered
import kotlinx.coroutines.Job

class ViewModelEmployee : ViewModel() {
    lateinit var job: Job
    val mutableLiveData = MutableLiveData<List<ModelEmployee>>()
    fun ListDaftarche(idcategory:String) {
        job = MainThered.CoroutinesHandel({
            HandelRequest.Request(Api.invoke().ListEmployee(idcategory))
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