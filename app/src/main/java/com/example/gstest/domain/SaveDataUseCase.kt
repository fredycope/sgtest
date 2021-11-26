package com.example.gstest.domain

import com.example.gstest.data.repository.DataBaseRepository
import com.example.masterdetail.dbroom.dbmodel.GSTest
import javax.inject.Inject

class SaveDataUseCase @Inject constructor(private val dataBaseRepository: DataBaseRepository) {

    suspend fun saveData(gsTest: GSTest){
         dataBaseRepository.saveData(gsTest)
    }
}