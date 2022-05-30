package com.androiod.room_cpp

import android.content.Context
import androidx.room.Database
import androidx.room.Room

class Repository() {

    val db = Room.databaseBuilder(
        App.instance.applicationContext,
        DataBase::class.java,
        "db"
    ).build()

    fun insert(){
        db.sampleDao().insert(Sample(6))
        db.sampleDao().insert(Sample(8))
        db.sampleDao().insert(Sample(10))
    }
}