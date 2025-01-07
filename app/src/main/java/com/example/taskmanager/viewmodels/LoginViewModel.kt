package com.example.taskmanager.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskmanager.repositories.AuthRepository
import kotlinx.coroutines.launch


class LoginViewModel : ViewModel() {

    fun login(email: String, password: String, onResult: (Boolean, String?) -> Unit) {
        viewModelScope.launch {
            AuthRepository.login(
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

    fun logout() {
        AuthRepository.logout()
    }

    fun isUserLoggedIn(): Boolean {
        return AuthRepository.currentUser != null
    }
}
