package com.example.myapplication.database

import com.example.myapplication.dao.UserDao
import com.example.myapplication.entities.User

class UserRepositoryImpl(private val userDao: UserDao) : UserRepository{

    override suspend fun insert(user: User) {
        userDao.insertUser(user)
    }

    override suspend fun getAllUsers() = userDao.getAllUsers()

    override suspend fun checkUserExist(userEmail: String, userPassword: String) = userDao.checkUserExist(userEmail, userPassword)
}