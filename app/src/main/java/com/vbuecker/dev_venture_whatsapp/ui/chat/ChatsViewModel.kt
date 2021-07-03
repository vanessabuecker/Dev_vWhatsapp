package com.vbuecker.dev_venture_whatsapp.ui.chat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vbuecker.dev_venture_whatsapp.data.model.Message
import com.vbuecker.dev_venture_whatsapp.data.repository.ChatRepository
import com.vbuecker.dev_venture_whatsapp.data.repository.UserRepository

class ChatsViewModel(val chatRepository: ChatRepository, val userRepository: UserRepository) : ViewModel() {
    val myEmail = userRepository.myEmail()

    private var _messageList = MutableLiveData<ArrayList<Message>>()

    fun getMessageData(chatId: String) {
        chatRepository.getMessages(chatId) {
            _messageList.value = it
        }
    }

    val messageList: LiveData<ArrayList<Message>>
        get() = _messageList
}