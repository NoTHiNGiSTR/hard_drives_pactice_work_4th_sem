package com.example.myapplication.database

import com.example.myapplication.entities.HardDrive

interface HardDriveRepository {

    suspend fun getModelById(modelId: Int): HardDrive?

    suspend fun getAllModels():List<HardDrive>

    suspend fun insertModel(hardDrive: HardDrive)
}