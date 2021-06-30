package com.vbuecker.dev_venture_whatsapp

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.firebase.ui.auth.AuthUI
import com.vbuecker.dev_venture_whatsapp.databinding.ActivityLoginBinding
import com.vbuecker.dev_venture_whatsapp.ui.login.RC_SIGN_IN

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val providers = arrayListOf(
        AuthUI.IdpConfig.EmailBuilder().build(),
        AuthUI.IdpConfig.GoogleBuilder().build()
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val login = binding.buttonLogin

        login.setOnClickListener {
            startActivityForResult(
                login(), RC_SIGN_IN
            )
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    private fun login(): Intent {
        return AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .build()
    }
}