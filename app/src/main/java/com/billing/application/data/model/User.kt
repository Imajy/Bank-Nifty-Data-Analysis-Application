package com.billing.application.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken
import com.google.gson.Gson
import java.time.LocalDate


@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    var up: String = "",
    var down: String = "",
    var call: String = "",
    var put: String = "",
    var date:String = "",
    var uplist: List<String> = listOf(),
    var downList : List<String> = listOf()
)
class Converters {

    @TypeConverter
    fun fromTargetNumValList(value: List<String>): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toTargetNumValList(value: String): List<String> {
        val listType = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(value, listType)
    }
}