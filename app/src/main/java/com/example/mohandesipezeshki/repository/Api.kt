package com.example.mohandesipezeshki.repository

import com.example.mohandesipezeshki.model.*
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface Api {
    @FormUrlEncoded
    @POST("register.php")
    suspend fun Register(@Field("phone")phone:String, @Field("password")password:String): Response<ModelStatus>
    @FormUrlEncoded
    @POST("checkcode.php")
    suspend fun CheckCode(@Field("phone")phone:String, @Field("code")code:String): Response<ModelStatus>
    @FormUrlEncoded
    @POST("userinfo.php")
    suspend fun Profile(@Field("token")token:String):Response<List<ModelUserInfo>>
    @FormUrlEncoded
    @POST("checkphone.php")
    suspend fun ChechPhone(@Field("phone")phone:String):Response<ModelStatus>
    @FormUrlEncoded
    @POST("checkcodeforget.php")
    suspend fun ChechCodeForget(@Field("phone")phone:String,@Field("code")code:String):Response<ModelStatus>
    @FormUrlEncoded
    @POST("changepassword.php")
    suspend fun ChangePassword(@Field("phone")phone:String,@Field("password")password:String):Response<ModelStatus>
    @FormUrlEncoded
    @POST("login.php")
    suspend fun Login(@Field("phone")phone:String,@Field("password")password:String):Response<ModelStatus>
    @GET("category.php")
    suspend fun ListDaftarche():Response<List<ModelDaftarche>>
    @FormUrlEncoded
    @POST("postdetails.php")
    suspend fun ListEmployee(@Field("idcategory")idcategory:String):Response<List<ModelEmployee>>
    @FormUrlEncoded
    @POST("employeeDetail.php")
    suspend fun PostDetials(@Field("id")id:String):Response<ModelEmDetails>

    companion object{
        operator fun invoke():Api{
            return Retrofit.Builder()
                .baseUrl("http://pixelli.ir/mohandesipezeshki/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Api::class.java)
        }
    }
}