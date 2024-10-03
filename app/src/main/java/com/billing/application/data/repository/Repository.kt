package com.billing.application.data.repository

import androidx.lifecycle.LiveData
import com.billing.application.data.dao.UserDao
import com.billing.application.data.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class UserRepository @Inject constructor(private val userDao: UserDao) {

    suspend fun insertUser(user: User) {
        withContext(Dispatchers.IO) {  // Run this code in IO thread
            userDao.insert(user)
        }
    }

    suspend fun updateUser(user: User) {
        withContext(Dispatchers.IO) {  // Run this code in IO thread
            userDao.update(user)
        }
    }
    // Fetch all users (for plain list)
    suspend fun getUsers(): List<User> {
        return withContext(Dispatchers.IO) {
            userDao.getAllUsers()
        }
    }

    // Observe all users (for LiveData)
    fun observeAllUsers(): LiveData<List<User>> {
        return userDao.observeAllUsers()
    }

    fun getUsersByDate(date: String): LiveData<List<User>> {
        return userDao.getUsersByDate(date)
    }
}