package com.vbuecker.dev_venture_whatsapp.ui.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.vbuecker.dev_venture_whatsapp.MainActivity
import com.vbuecker.dev_venture_whatsapp.RC_SIGN_IN
import com.vbuecker.dev_venture_whatsapp.data.model.User
import com.vbuecker.dev_venture_whatsapp.data.repository.UserRepository
import com.vbuecker.dev_venture_whatsapp.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding
        get() = _binding!!

    private val providers = arrayListOf(
        AuthUI.IdpConfig.EmailBuilder().build(),
        AuthUI.IdpConfig.GoogleBuilder().build()
    )


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initComponents()
    }

    private fun initComponents() {
        val login = binding.buttonLogin

        login.setOnClickListener {
            startActivityForResult(
                login(), RC_SIGN_IN
            )
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            val current = FirebaseAuth.getInstance().currentUser?.apply {
                val user: User =
                    User(this.displayName ?: "No name", this.email ?: "No email", this.uid)
                UserRepository.addUser(
                    user,
                    { onSuccess() }) { onFail("Não foi possível adicionar o usuário") }
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

    private fun onFail(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    private fun onSuccess() {
        Toast.makeText(context, "Sucesso", Toast.LENGTH_LONG).show()
//        val intent = Intent(this, MainActivity::class.java)
//        startActivity(intent)
    }
}