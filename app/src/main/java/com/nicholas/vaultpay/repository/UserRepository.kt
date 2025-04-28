package com.nicholas.vaultpay.repository

import com.nicholas.vaultpay.data.UserDao
import com.nicholas.vaultpay.model.User

class UserRepository(private val userDao: UserDao) {
    suspend fun registerUser(user: User) {
        userDao.registerUser(user)
    }

    suspend fun loginUser(email: String, password: String): User? {
        return userDao.loginUser(email, password)
    }
}