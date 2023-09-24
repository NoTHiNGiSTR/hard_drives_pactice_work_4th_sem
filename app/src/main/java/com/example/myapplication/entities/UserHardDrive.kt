package com.example.myapplication.entities

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "user_hard_drive", primaryKeys = ["user_id", "model_id"])
data class UserHardDrive(
    @ColumnInfo(name = "user_id")
    val user_id: Int,
    @ColumnInfo(name = "model_id")
    val model_id: Int
)
