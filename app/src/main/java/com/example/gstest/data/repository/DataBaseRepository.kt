package com.example.gstest.data.repository

import com.example.masterdetail.dbroom.dbdao.GsDataDao
import com.example.masterdetail.dbroom.dbmodel.GSTest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DataBaseRepository @Inject constructor(val gsDataDao: GsDataDao) {
    suspend fun getData(): List<GSTest>{
        return withContext(Dispatchers.IO){
            gsDataDao.getListMov()
        }
    }

    suspend fun saveData(gsTest: GSTest){
        withContext(Dispatchers.IO){
            gsDataDao.insert(gsTest)
        }
    }

    suspend fun deleteData(){
        withContext(Dispatchers.IO){
            gsDataDao.deleteAll()
        }
    }

}