package com.vbuecker.dev_venture_whatsapp.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.vbuecker.dev_venture_whatsapp.data.repository.UserRepository

class LoginViewModel(val userRepository: UserRepository) : ViewModel() {
    var isUserAuthenticatedLiveData: LiveData<Boolean>? = null

    fun checkIfAuthenticated() {
        isUserAuthenticatedLiveData = userRepository.checkIfUserIsAuthenticated()
    }

    fun saveUser() {
       userRepository.saveUser()
    }
}