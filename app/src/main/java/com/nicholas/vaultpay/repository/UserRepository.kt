package com.nicholas.vaultpay.repository


import com.nicholas.vaultpay.data.UserDao
import com.nicholas.vaultpay.model.User

class UserRepository(private val userDao: UserDao) {

    suspend fun registerUser(user: User) {
        userDao.insertUser(user)
    }

    suspend fun loginUser(email: String, password: String): User? {
        return userDao.loginUser(email, password)

        suspend fun getUserByEmail(email: String): User? {
            return userDao.getUserByEmail(email)
        }

        suspend fun getUserByUsername(username: String): User? {
            return userDao.getUserByUsername(username)
        }

        suspend fun getAllUsers(): List<User> {
            return userDao.getAllUsers()
        }
    }
}