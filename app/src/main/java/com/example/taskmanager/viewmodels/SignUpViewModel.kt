package com.example.taskmanager.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskmanager.repositories.AuthRepository
import kotlinx.coroutines.launch

class SignupViewModel : ViewModel() {

    fun signUp(email: String, password: String, onResult: (Boolean, String?) -> Unit) {
        viewModelScope.launch {
            AuthRepository.signUp(
                email,
                password,
                onSuccess = {
                    onResult(true, null)
                },
                onFailure = { e ->
                    onResult(false, e.message)
                }
            )
        }
    }
}