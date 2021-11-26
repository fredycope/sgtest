package com.example.masterdetail.dbroom.dbmodel

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = GSTest.TABLE_NAME)
class
GSTest(
    @ColumnInfo(name = "originalTitle") val originalTitle: String,
    @ColumnInfo(name = "overview") val overview: String,
    @ColumnInfo(name = "posterPath") val posterPath: String,
    @ColumnInfo(name = "releaseDate") val releaseDate: String
) {
        companion object{
            const val TABLE_NAME = "movies"
        }
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "mov_id")
        var movId = 0
}