package com.androiod.room_cpp

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Sample::class], version = 1, exportSchema = false)
abstract class DataBase : RoomDatabase() {
    abstract fun sampleDao():  Dao
}