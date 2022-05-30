package com.androiod.room_cpp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Sample(
    @PrimaryKey
    val id: Int,
)
