package com.example.mohandesipezeshki.repository

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.navigation.NavController
import com.example.mohandesipezeshki.view.fragment.FragmentProfile
import retrofit2.Response

abstract class Repository {
    private lateinit var navController: NavController
    suspend fun <T:Any> CustomRequest(Api:()->Response<T>):T{
        val response = Api.invoke()
        if(response.isSuccessful)
            return response.body()!!
        throw Exception(response.message())
    }
    companion object{
        fun shardwrite(content : Context, token:String){
            val shard = content.getSharedPreferences("info",0)
            val editor = shard.edit()
            editor.putString("token",token)
            editor.apply()
        }

        fun shardread(content : Context) : String{
            val shard = content.getSharedPreferences("info",0)
            val token = shard.getString("token",null)
            token?.let {
                return token
            }
            return "notfind"
        }



    }
}