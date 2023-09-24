package com.example.myapplication.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.myapplication.entities.HardDrive

@Dao
interface HardDriveDao {
    @Query("SELECT * FROM hard_drives")
    fun getAllHardDrives(): List<HardDrive>

    @Query("SELECT * FROM hard_drives WHERE id = :modelId")
    fun getModelById(modelId: Int): HardDrive?

    @Insert
    fun InsertHardDrive(hardDrive: HardDrive)

    @Delete
    fun DeleteHardDrive(hardDrive: HardDrive)
}