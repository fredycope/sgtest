package com.example.gstest.domain

import com.example.gstest.data.repository.DataBaseRepository
import com.example.masterdetail.dbroom.dbmodel.GSTest
import javax.inject.Inject

class GetDataUseCase @Inject constructor(private val dataBaseRepository: DataBaseRepository) {

    suspend fun getData(): List<GSTest>{
        return dataBaseRepository.getData()
    }
}