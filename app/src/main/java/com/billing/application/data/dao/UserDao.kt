package com.billing.application.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.billing.application.data.model.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(user: User)

    // Query to get all users from the 'users' table
    @Query("SELECT * FROM users")
    fun getAllUsers(): List<User>  // If you want a list of users without observing

    // Optionally, if you want to observe changes in the data, use LiveData
    @Query("SELECT * FROM users")
    fun observeAllUsers(): LiveData<List<User>>

    @Query("SELECT * FROM users WHERE date = :date")
    fun getUsersByDate(date: String): LiveData<List<User>>

}