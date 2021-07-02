package com.vbuecker.dev_venture_whatsapp.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.vbuecker.dev_venture_whatsapp.fragments.ChatsFragment
import com.vbuecker.dev_venture_whatsapp.fragments.StatusFragment
import com.vbuecker.dev_venture_whatsapp.fragments.ContactsFragment

class ViewPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment = when (position) {
        0 -> ChatsFragment()
        //1 -> ContactsFragment()
        else -> StatusFragment()
    }


}