package com.example.myapplication.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.dao.HardDriveDao
import com.example.myapplication.dao.UserDao
import com.example.myapplication.dao.UserHardDriveDao
import com.example.myapplication.entities.HardDrive
import com.example.myapplication.entities.User
import com.example.myapplication.entities.UserHardDrive


@Database(entities = [User::class, HardDrive::class, UserHardDrive::class], version = 1)
abstract class MainDB : RoomDatabase() {


    companion object{
        fun getDb(context: Context): MainDB{
            return Room.databaseBuilder(context, MainDB::class.java, "kursachDB.db").fallbackToDestructiveMigration()
         .createFromAsset("kursachDB.db").build()

        }
    }

    abstract fun userDao(): UserDao
    abstract fun hardDriveDao(): HardDriveDao
    abstract fun userHardDriveDao(): UserHardDriveDao
}