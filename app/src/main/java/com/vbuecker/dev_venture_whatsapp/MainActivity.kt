package com.vbuecker.dev_venture_whatsapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vbuecker.dev_venture_whatsapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
    }

}
