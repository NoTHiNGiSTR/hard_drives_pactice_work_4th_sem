package com.example.myapplication.database

import com.example.myapplication.entities.HardDrive
import com.example.myapplication.entities.UserHardDrive

interface UserHardDriveRepository {
    suspend fun insert(useHardDrive: UserHardDrive)

    suspend fun getFavouriteHardDrivesByUserId(userId: Int): List<HardDrive>

    suspend fun isHardDriveFavouriteByUser(userId: Int, hardDriveId: Int): UserHardDrive?

    suspend fun delete(useHardDrive: UserHardDrive)
}