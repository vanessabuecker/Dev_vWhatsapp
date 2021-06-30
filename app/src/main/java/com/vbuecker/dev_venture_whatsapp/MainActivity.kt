package com.vbuecker.dev_venture_whatsapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
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

        supportActionBar?.title = "Whatsapp - Group 1"
        actionBar?.setDisplayHomeAsUpEnabled(true)

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

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
