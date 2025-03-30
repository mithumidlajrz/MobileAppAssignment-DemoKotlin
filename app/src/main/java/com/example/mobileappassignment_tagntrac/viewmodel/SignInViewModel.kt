package com.example.mobileappassignment_tagntrac.viewmodel

import androidx.lifecycle.*
import com.example.mobileappassignment_tagntrac.model.mockUsers
import com.example.mobileappassignment_tagntrac.model.workspaceIdMockUsers
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SignInViewModel: ViewModel() {

    private val _isLoggingIn = MutableStateFlow(false)
    val isLoggingIn: StateFlow<Boolean> = _isLoggingIn

    fun login(email: String, password: String, onResult: (Boolean, String?) -> Unit) {
        val user = mockUsers.firstOrNull {
            it.email.equals(
                email,
                ignoreCase = true
            ) && it.password == password
        }

        if (user != null) {
            _isLoggingIn.value = true
            viewModelScope.launch {
                delay(1000) // Simulating network delay
                onResult(true, null)
                _isLoggingIn.value = false
            }
        } else {
            onResult(false, "Invalid credentials. Please try again.")
        }
    }

    fun workspaceIdLogin(id: String, onResult: (Boolean, String?) -> Unit) {
        if (workspaceIdMockUsers.contains(id)) {
            onResult(true, null)
        } else {
            onResult(false, "Invalid workspace ID. Please try again.")
        }
    }
}