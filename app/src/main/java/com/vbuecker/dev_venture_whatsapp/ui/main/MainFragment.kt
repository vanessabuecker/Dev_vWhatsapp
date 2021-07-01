package com.vbuecker.dev_venture_whatsapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.vbuecker.dev_venture_whatsapp.adapters.ViewPagerAdapter
import com.vbuecker.dev_venture_whatsapp.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewPager()
    }

    private fun initViewPager() {
        binding.viewpager.adapter = ViewPagerAdapter(childFragmentManager, lifecycle)

        TabLayoutMediator(
            binding.tabs,
            binding.viewpager
        ) { tab, pos ->
            when (pos) {
                0 -> tab.text = "CONVERSAS"
                1 -> tab.text = "STATUS"
                2 -> tab.text = "CHAMADAS"
            }
        }.attach()
    }
}