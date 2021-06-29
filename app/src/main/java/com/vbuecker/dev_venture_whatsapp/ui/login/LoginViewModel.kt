package com.vbuecker.dev_venture_whatsapp.ui.login

import androidx.lifecycle.ViewModel
import com.vbuecker.dev_venture_whatsapp.data.LoginRepository

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {

    fun login(username: String, password: String) {
        val result = loginRepository.login(username, password)
    }
}