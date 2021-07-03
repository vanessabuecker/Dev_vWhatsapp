package com.vbuecker.dev_venture_whatsapp.ui.chat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vbuecker.dev_venture_whatsapp.data.model.Message
import com.vbuecker.dev_venture_whatsapp.data.repository.ChatRepository

class ChatsViewModel(val chatRepository: ChatRepository) : ViewModel() {
    private var _messageList = MutableLiveData<ArrayList<Message>>()
    var chatId: String? = null


    fun getMessageData(chatId: String) {
        chatRepository.getMessages(chatId) {
            _messageList.value = it
        }
    }

    val messageList: LiveData<ArrayList<Message>>
        get() = _messageList
}