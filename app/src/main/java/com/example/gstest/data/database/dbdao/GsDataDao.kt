package com.example.masterdetail.dbroom.dbdao

import androidx.room.*
import com.example.masterdetail.dbroom.dbmodel.GSTest

@Dao
interface GsDataDao {
    @Insert
    suspend fun insert(gsTest: GSTest)

    @Update
    suspend fun update(vararg gsTest: GSTest)

    @Delete
    suspend fun delete(vararg gsTest: GSTest)

    @Query("DELETE FROM "+GSTest.TABLE_NAME+" WHERE mov_id = :gsId")
    suspend fun deleteId(gsId: String)

    @Query("SELECT * FROM " + GSTest.TABLE_NAME)
    suspend fun getListMov(): List<GSTest>

    @Query("DELETE FROM "+GSTest.TABLE_NAME)
    suspend fun deleteAll()
}