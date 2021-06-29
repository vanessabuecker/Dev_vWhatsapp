package com.vbuecker.dev_venture_whatsapp

import android.os.Bundle
import android.view.Menu
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import com.vbuecker.dev_venture_whatsapp.databinding.ActivityMainBinding
import com.vbuecker.dev_venture_whatsapp.ui.main.SectionsPagerAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

       // val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
      //  val viewPager: ViewPager = binding.viewPager
       // viewPager.adapter = sectionsPagerAdapter
       // val tabs: TabLayout = binding.tabs
        //tabs.setupWithViewPager(viewPager)
      //  val fab: FloatingActionButton = binding.fab

       // fab.setOnClickListener { view ->
         //   Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
            //    .setAction("Action", null).show()
        }
    }
