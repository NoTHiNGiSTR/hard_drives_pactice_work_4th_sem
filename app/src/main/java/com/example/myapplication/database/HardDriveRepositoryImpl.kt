package com.example.myapplication.database

import com.example.myapplication.dao.HardDriveDao
import com.example.myapplication.entities.HardDrive

class HardDriveRepositoryImpl(private val hardDriveDao: HardDriveDao): HardDriveRepository {

    override suspend fun getModelById(modelId: Int) = hardDriveDao.getModelById(modelId)

    override suspend fun getAllModels(): List<HardDrive> = hardDriveDao.getAllHardDrives()

    override suspend fun insertModel(hardDrive: HardDrive) {
        hardDriveDao.InsertHardDrive(hardDrive)
    }
}