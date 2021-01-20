package com.example.mohandesipezeshki.repository

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

object MainThered {
    fun <T : Any> CoroutinesHandel(Api: suspend (() -> T), unit: ((T) -> Unit)) =
        CoroutineScope(Dispatchers.Main).launch {
            val data = CoroutineScope(Dispatchers.IO).async rt@{
                return@rt Api()
            }.await()
            unit(data)
        }
}