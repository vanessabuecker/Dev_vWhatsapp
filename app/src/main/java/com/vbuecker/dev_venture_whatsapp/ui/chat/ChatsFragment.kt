package com.vbuecker.dev_venture_whatsapp.ui.chat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.vbuecker.dev_venture_whatsapp.data.repository.ChatRepository
import com.vbuecker.dev_venture_whatsapp.databinding.FragmentChatsBinding
import com.vbuecker.dev_venture_whatsapp.ui.login.LoginViewModel

class ChatsFragment : Fragment() {
    private var _binding: FragmentChatsBinding? = null
    private val binding
        get() = _binding!!
    private val viewModel: ChatsViewModel by viewModel()
    private val loginViewModel: LoginViewModel by viewModel()
    private lateinit var mAdapter: ChatsAdapter
    private var chatId: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChatsBinding.inflate(inflater, container, false)
        chatId = arguments?.getString("chatId")
        if (chatId.isNullOrEmpty())
            findNavController().popBackStack()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        configureSendMessage()
        subscribeObservers()
    }

    private fun initRecyclerView() {
        mAdapter = ChatsAdapter()
        binding.rvChat.apply {
            adapter = mAdapter
        }
    }

    private fun configureSendMessage() {
        val myEmail = loginViewModel.myEmail
        binding.btnSend.setOnClickListener {
            val msg = binding.tvMessage.text.toString()
            ChatRepository.addMessageToChat(chatId!!, myEmail, msg)
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