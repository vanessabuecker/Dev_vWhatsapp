package com.vbuecker.dev_venture_whatsapp.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.google.firebase.auth.FirebaseAuth
import com.vbuecker.dev_venture_whatsapp.MainActivity
import com.vbuecker.dev_venture_whatsapp.R
import com.vbuecker.dev_venture_whatsapp.LoginActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val user = FirebaseAuth.getInstance().currentUser

        Handler(Looper.getMainLooper()).postDelayed({
            val intent: Intent = if(user == null) {
                Intent(this, LoginActivity::class.java)
            } else {
                Intent(this, MainActivity::class.java)
            }
            startActivity(intent)
            finish()
        }, 1000)
    }


}