package com.vbuecker.dev_venture_whatsapp.ui.chat

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.vbuecker.dev_venture_whatsapp.data.repository.ChatRepository
import com.vbuecker.dev_venture_whatsapp.data.repository.UserRepository
import com.vbuecker.dev_venture_whatsapp.databinding.FragmentChatsBinding
import com.vbuecker.dev_venture_whatsapp.ui.contacts.ContactsAdapter

class ChatsFragment : Fragment() {
    private var _binding: FragmentChatsBinding? = null
    private val binding
        get() = _binding!!
    private lateinit var mAdapter: ChatsAdapter
    private var chatId: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChatsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        chatId = arguments?.getString("chatId")

        initRecyclerView()
        if (chatId.isNullOrEmpty()) {
            findNavController().popBackStack()
            return
        }

        val me = UserRepository.myEmail()

        binding.btnSend.setOnClickListener {
            val msg = binding.tvMessage.text.toString()
            ChatRepository.addMessageToChat(chatId!!, me, msg)
        }
    }

    private fun initRecyclerView() {
        mAdapter = ChatsAdapter()
        ChatRepository.getMessages(chatId!!) {
            mAdapter.setDataset(it)
        }

        binding.rvChat.apply {
            adapter = mAdapter
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}