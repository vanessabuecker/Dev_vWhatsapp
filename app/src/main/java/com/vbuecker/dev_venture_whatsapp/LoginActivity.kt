package com.vbuecker.dev_venture_whatsapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.vbuecker.dev_venture_whatsapp.data.repository.UserRepository
import com.vbuecker.dev_venture_whatsapp.databinding.ActivityLoginBinding
import com.vbuecker.dev_venture_whatsapp.data.model.User

const val RC_SIGN_IN = 123
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

        if(resultCode == Activity.RESULT_OK) {
            val current = FirebaseAuth.getInstance().currentUser?.apply {
                val user: User = User(this.displayName ?: "No name", this.email ?: "No email", this.uid)
                UserRepository.addUser(user, {onSuccess()}) { onFail("Não foi possível adicionar o usuário") }
            }
        } else {
            onFail("Falhou")
        }
    }

    private fun login(): Intent {
        return AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .build()
    }

    fun onFail(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    fun onSuccess() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}