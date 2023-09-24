package com.example.myapplication.database

import com.example.myapplication.entities.User

interface UserRepository {

    suspend fun insert(user: User)

    suspend fun getAllUsers(): List<User>

    suspend fun checkUserExist(userEmail: String, userPassword: String): User?
}