package com.billing.application.common

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : ViewModel() {
    fun setDate(update:String){
        sharedPreferences.edit().putString("date",update).apply()
    }
    fun getDate():String{
        return sharedPreferences.getString("date","").toString()
    }
    fun setUp(update:String){
        sharedPreferences.edit().putString("up",update).apply()
    }
    fun getUp():String{
        return sharedPreferences.getString("up","").toString()
    }
    fun setDown(update:String){
        sharedPreferences.edit().putString("down",update).apply()
    }
    fun getDown():String{
        return sharedPreferences.getString("down","").toString()
    }
    fun setPut(update:String){
        sharedPreferences.edit().putString("put",update).apply()
    }
    fun getPut():String{
        return sharedPreferences.getString("put","").toString()
    }
    fun setCall(update:String){
        sharedPreferences.edit().putString("call",update).apply()
    }
    fun getCall():String{
        return sharedPreferences.getString("call","").toString()
    }
    fun setEditType(update:Boolean){
        sharedPreferences.edit().putBoolean("editType",update).apply()
    }
    fun getEditType():Boolean{
        return sharedPreferences.getBoolean("editType",false)
    }
}