package com.example.gstest.di

import android.content.Context
import androidx.room.Room
import com.example.masterdetail.dbroom.db.GsDataBase
import com.example.masterdetail.dbroom.dbdao.GsDataDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class DataBaseModule {

    @Provides
    fun provideAppDatabase(@ApplicationContext appContext: Context): GsDataBase {
        return Room.databaseBuilder(
            appContext,
            GsDataBase::class.java,
            "GSTest"
        ).allowMainThreadQueries().build()
    }

    @Provides
    fun provideGsDataDao(gsDataBase: GsDataBase): GsDataDao {
        return gsDataBase.gsDataDao()
    }

}