package com.example.masterdetail.dbroom.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.masterdetail.dbroom.dbdao.GsDataDao
import com.example.masterdetail.dbroom.dbmodel.GSTest

@Database(entities = [GSTest::class], version = 1, exportSchema = false)
abstract class GsDataBase : RoomDatabase(){
    abstract fun gsDataDao(): GsDataDao
}