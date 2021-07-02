package com.vbuecker.dev_venture_whatsapp.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.vbuecker.dev_venture_whatsapp.fragments.ChatsFragment
import com.vbuecker.dev_venture_whatsapp.fragments.StatusFragment

class ViewPagerAdapter(fa: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fa, lifecycle) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment = when (position) {
        0 -> ChatsFragment()
//        1 -> UserFragment()
        else -> StatusFragment()
    }


}