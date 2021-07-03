package com.vbuecker.dev_venture_whatsapp.ui.contacts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.vbuecker.dev_venture_whatsapp.R
import com.vbuecker.dev_venture_whatsapp.data.model.Contact
import com.vbuecker.dev_venture_whatsapp.data.repository.ChatRepository
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.vbuecker.dev_venture_whatsapp.databinding.FragmentContactsBinding

class ContactsFragment : Fragment() {

    private var _binding: FragmentContactsBinding? = null
    private val binding
        get() = _binding!!

    private lateinit var mAdapter: ContactsAdapter
    private val viewModel: ContactsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentContactsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initRecyclerView()
        subscriberObservers()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initRecyclerView() {
        mAdapter = ContactsAdapter { contact -> onContactSelected(contact) }
        binding.contactList.apply {
            adapter = mAdapter
        }
    }

    private fun subscriberObservers() {
        viewModel.contactsList.observe(viewLifecycleOwner, Observer { contactList ->
            mAdapter.setDataset(contactList)
        })
    }

    private fun onContactSelected(contact: Contact) {
        ChatRepository.getChatWith(contact.email) { chatId, e ->
            if (e != null)
                Toast.makeText(context, e, Toast.LENGTH_LONG).show()
            else
                goToChat(chatId)
        }
    }

    private fun goToChat(chatId: String) {
        val bundle = bundleOf("chatId" to chatId)
        findNavController().navigate(R.id.action_contactsFragment_to_chatsFragment, bundle)
    }

}