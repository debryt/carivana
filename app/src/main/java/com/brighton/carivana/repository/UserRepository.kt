package com.brighton.carivana.repository

import com.brighton.carivana.data.UserDao
import com.brighton.carivana.model.User

class UserRepository(private val userDao: UserDao) {

    suspend fun registerUser(user: User) {
        userDao.registerUser(user)
    }

    suspend fun loginUser(email: String, password: String): User? {
        return userDao.loginUser(email, password)
    }

    // Add the method to fetch all users
    suspend fun getAllUsers(): List<User> {
        return userDao.getAllUsers()
    }
}
