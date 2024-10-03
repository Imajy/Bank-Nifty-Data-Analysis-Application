package com.billing.application.data.dao

import android.app.Activity
import androidx.room.Room
import com.billing.application.data.AppDatabase

object DatabaseManager {
    private var instance: AppDatabase? = null

    fun getInstance(context: Activity): AppDatabase {
        if (instance == null) {
            instance = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "app_database"
            ).build()
        }
        return instance!!
    }
}