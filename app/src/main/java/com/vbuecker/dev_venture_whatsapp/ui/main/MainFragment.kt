package com.vbuecker.dev_venture_whatsapp.ui.main

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.firebase.ui.auth.AuthUI
import com.google.android.material.tabs.TabLayoutMediator
import com.vbuecker.dev_venture_whatsapp.R
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
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewPager()
        binding.fabContacts.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_contactsFragment)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater?.inflate(R.menu.menu_logout, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_logout) {
            AuthUI.getInstance().signOut(requireContext()).addOnCompleteListener{
                Toast.makeText(requireContext(), "Usuário deslogado", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_mainFragment_to_loginFragment)
            }
        }
            return NavigationUI.onNavDestinationSelected(
                item,
                findNavController()
            )
                    || super.onOptionsItemSelected(item)
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