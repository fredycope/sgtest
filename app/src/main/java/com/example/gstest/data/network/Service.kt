package com.example.gstest.data.network

import RetrofitService.data.network.RetrofitService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class Service @Inject constructor(private val retrofitService: RetrofitService)  {
    suspend fun getCharacter(): Any{
        return withContext(Dispatchers.IO) {
            retrofitService.getCharecter()
        }
    }
}