package com.example.mohandesipezeshki.repository

import retrofit2.Response

object HandelRequest: Repository() {
    suspend fun <T:Any> Request(Api:Response<T>) = CustomRequest {
        Api
    }
}