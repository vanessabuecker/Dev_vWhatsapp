package com.vbuecker.dev_venture_whatsapp.ui.login

import android.content.Intent
import androidx.lifecycle.ViewModel
import com.firebase.ui.auth.AuthUI
import com.vbuecker.dev_venture_whatsapp.data.LoginRepository

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {
    private val providers = arrayListOf(
        AuthUI.IdpConfig.EmailBuilder().build(),
        AuthUI.IdpConfig.GoogleBuilder().build()
    )

    fun login(): Intent {
        return AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .build()
    }
}