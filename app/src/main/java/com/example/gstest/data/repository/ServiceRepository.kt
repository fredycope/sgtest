package com.example.gstest.data.repository

import com.example.gstest.data.network.Service
import javax.inject.Inject

class ServiceRepository @Inject constructor(private val service: Service) {
    suspend fun getCharacter(): Any{
        return service.getCharacter()
    }
}