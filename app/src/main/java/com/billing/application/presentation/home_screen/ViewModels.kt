package com.billing.application.presentation.home_screen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.billing.application.data.dao.UserDao
import com.billing.application.data.model.User
import com.billing.application.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModels @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {
    // For observing changes in user data (LiveData)
    val allUsers: LiveData<List<User>> = userRepository.observeAllUsers()
    var users: List<User> = emptyList()

    // For fetching users synchronously (needs to be called from a coroutine)
    fun getUsers(lists:(List<User>) -> Unit) {
        viewModelScope.launch {
            lists(userRepository.getUsers())
            // Do something with the users list
        }
    }

    fun insertUser(user: User) {
        viewModelScope.launch {
            userRepository.insertUser(user)
        }
    }

    fun updateUsers(user: User) {
        viewModelScope.launch {
            try {
                userRepository.updateUser(user)
                Log.d("ViewModels", "User updated successfully: $user")
            } catch (e: Exception) {
                Log.e("ViewModels", "Error updating user: ${e.message}")
            }
        }
    }
}