package com.vbuecker.dev_venture_whatsapp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.firebase.ui.auth.AuthUI
import com.google.android.material.tabs.TabLayoutMediator
import com.vbuecker.dev_venture_whatsapp.adapters.ViewPagerAdapter
import com.vbuecker.dev_venture_whatsapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val pager by lazy {
        findViewById<ViewPager2>(R.id.viewpager)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        title = "Whatsapp - Group 1"

        pager.adapter = ViewPagerAdapter(this)

        TabLayoutMediator(
            findViewById(R.id.tabs),
            pager,
            TabLayoutMediator.TabConfigurationStrategy { tab, pos ->
                when (pos) {
                    0 -> tab.text = "CONVERSAS"
                    1 -> tab.text = "STATUS"
                    2 -> tab.text = "CHAMADAS"
                }
            }).attach()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.action_logout) {
            AuthUI.getInstance().signOut(this).addOnCompleteListener {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
            return true
        }
        return super.onOptionsItemSelected(item)
    }



}
