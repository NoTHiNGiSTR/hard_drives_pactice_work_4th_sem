package com.example.myapplication.app

import android.app.Application
import com.example.myapplication.database.*
import com.example.myapplication.entities.HardDrive


class App : Application() {

    companion object{
        private lateinit var database: MainDB
        lateinit var userRepository: UserRepository
        lateinit var hardDriveRepository: HardDriveRepository
        lateinit var userHardDriveRepository: UserHardDriveRepository
        lateinit var compareStack: ArrayDeque<HardDrive>
    }



    override fun onCreate() {
        super.onCreate()

        database = MainDB.getDb(applicationContext)

        compareStack = ArrayDeque(listOf())

        userRepository = UserRepositoryImpl(database.userDao())

        hardDriveRepository = HardDriveRepositoryImpl(database.hardDriveDao())

        userHardDriveRepository = UserHardDriveRepositoryImpl(database.userHardDriveDao())

    }



}