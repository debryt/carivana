package com.brighton.carivana.repository

import com.brighton.carivana.data.UserDao
import com.brighton.carivana.model.User


class UserRepository(private val userDao: UserDao) {
    suspend fun registerUser(user: User) = userDao.registerUser(user)
    suspend fun loginUser(name: String, password: String) = userDao.loginUser(name, password)
    suspend fun updateUser(user: User) = userDao.updateUser(user)
    suspend fun deleteUser(user: User) = userDao.deleteUser(user)
    suspend fun getUserById(id: Int) = userDao.getUserById(id)
}