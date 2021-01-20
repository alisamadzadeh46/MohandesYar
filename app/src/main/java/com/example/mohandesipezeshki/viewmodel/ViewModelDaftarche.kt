package com.example.mohandesipezeshki.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mohandesipezeshki.model.ModelDaftarche
import com.example.mohandesipezeshki.repository.Api
import com.example.mohandesipezeshki.repository.HandelRequest
import com.example.mohandesipezeshki.repository.MainThered
import kotlinx.coroutines.Job

class ViewModelDaftarche : ViewModel() {
    lateinit var job: Job
    val mutableLiveData = MutableLiveData<List<ModelDaftarche>>()
    fun ListDaftarche() {
        job = MainThered.CoroutinesHandel({
            HandelRequest.Request(Api.invoke().ListDaftarche())
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