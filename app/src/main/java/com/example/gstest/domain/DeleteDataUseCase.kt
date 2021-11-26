package com.example.gstest.domain

import com.example.gstest.data.repository.DataBaseRepository
import javax.inject.Inject

class DeleteDataUseCase @Inject constructor(private val dataBaseRepository: DataBaseRepository) {
    suspend fun deleteData(){
        dataBaseRepository.deleteData()
    }
}