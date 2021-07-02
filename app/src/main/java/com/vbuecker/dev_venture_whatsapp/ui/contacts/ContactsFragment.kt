package com.vbuecker.dev_venture_whatsapp.ui.contacts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.vbuecker.dev_venture_whatsapp.data.model.Contact
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.vbuecker.dev_venture_whatsapp.databinding.FragmentContactsBinding

class ContactsFragment : Fragment() {

    private var _binding: FragmentContactsBinding? = null
    private val binding
        get() = _binding!!

    private val mAdapter = ContactsAdapter()
    private val viewModel: ContactsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentContactsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        subscriberObservers()
        initRecyclerView()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initRecyclerView() {
        binding.contactList.apply {
            adapter = mAdapter
        }
    }

    private fun subscriberObservers() {
        viewModel.contactsList.observe(viewLifecycleOwner, Observer { contactList ->
            mAdapter.setDataset(contactList)
        })
    }

}