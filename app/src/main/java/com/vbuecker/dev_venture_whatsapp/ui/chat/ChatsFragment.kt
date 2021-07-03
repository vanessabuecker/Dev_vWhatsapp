package com.vbuecker.dev_venture_whatsapp.ui.chat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.vbuecker.dev_venture_whatsapp.databinding.FragmentChatsBinding

class ChatsFragment : Fragment() {
    private var _binding: FragmentChatsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ChatsViewModel by viewModel()
    private lateinit var mAdapter: ChatsAdapter
    private var chatId: String? = null

    private lateinit var myEmail: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChatsBinding.inflate(inflater, container, false)
        checkIfChatIdExists()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        myEmail = viewModel.myEmail
        initRecyclerView()
        configureSendMessage()
        subscribeObservers()
    }

    private fun checkIfChatIdExists() {
        chatId = arguments?.getString("chatId")
        if (chatId.isNullOrEmpty())
            findNavController().popBackStack()
    }

    private fun initRecyclerView() {
        mAdapter = ChatsAdapter(myEmail)
        binding.rvChat.apply {
            adapter = mAdapter
            scrollToPosition(mAdapter.itemCount - 1)
        }
    }

    private fun configureSendMessage() {
        binding.btnSend.setOnClickListener {
            val msg = binding.tvMessage.text.toString()
            viewModel.addMessageToChat(chatId!!, myEmail, msg)
            binding.rvChat.scrollToPosition(mAdapter.itemCount - 1)
            binding.tvMessage.text.clear()
        }
    }

    private fun subscribeObservers() {
        viewModel.getMessageData(chatId!!)
        viewModel.messageList.observe(viewLifecycleOwner, Observer {
            mAdapter.setDataset(it)
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}