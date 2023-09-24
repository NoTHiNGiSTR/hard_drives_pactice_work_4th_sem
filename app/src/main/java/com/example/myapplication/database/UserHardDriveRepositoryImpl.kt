package com.example.myapplication.database

import com.example.myapplication.dao.UserHardDriveDao
import com.example.myapplication.entities.UserHardDrive

class UserHardDriveRepositoryImpl(private val userHardDriveDao: UserHardDriveDao) : UserHardDriveRepository {
    override suspend fun insert(useHardDrive: UserHardDrive) {
        userHardDriveDao.insert(useHardDrive)
    }

    override suspend fun getFavouriteHardDrivesByUserId(userId: Int) = userHardDriveDao.getFavoriteHardDrivesByUserId(userId)

    override suspend fun isHardDriveFavouriteByUser(userId: Int, hardDriveId: Int) = userHardDriveDao.isHardDriveFavoriteByUser(userId, hardDriveId)

    override suspend fun delete(useHardDrive: UserHardDrive) = userHardDriveDao.delete(useHardDrive)

}