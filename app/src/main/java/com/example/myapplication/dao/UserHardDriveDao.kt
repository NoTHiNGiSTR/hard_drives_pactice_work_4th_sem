package com.example.myapplication.dao

import androidx.room.*
import com.example.myapplication.entities.HardDrive
import com.example.myapplication.entities.UserHardDrive

@Dao
interface UserHardDriveDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(userHardDrive: UserHardDrive)

    @Delete
    suspend fun delete(userHardDrive: UserHardDrive)

    @Query("SELECT * FROM hard_drives WHERE id IN (SELECT model_id FROM user_hard_drive WHERE user_id = :userId)")
    suspend fun getFavoriteHardDrivesByUserId(userId: Int): List<HardDrive>

    @Query("SELECT * FROM user_hard_drive WHERE user_id = :userId AND model_id = :hardDriveId")
    suspend fun isHardDriveFavoriteByUser(userId: Int, hardDriveId: Int): UserHardDrive?

}