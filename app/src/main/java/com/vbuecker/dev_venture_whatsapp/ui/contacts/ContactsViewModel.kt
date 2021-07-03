package com.vbuecker.dev_venture_whatsapp.ui.contacts

import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vbuecker.dev_venture_whatsapp.data.model.Contact
import com.vbuecker.dev_venture_whatsapp.data.repository.ChatRepository
import com.vbuecker.dev_venture_whatsapp.data.repository.UserRepository

class ContactsViewModel(val userRepository: UserRepository, val chatRepository: ChatRepository) : ViewModel() {
    fun getChatWith(email: String, onComplete: (chatId: String, e: String?) -> Unit) {
        chatRepository.getChatWith(email) { chatId, e ->
            onComplete(chatId, e)
        }
    }

    private val _contactsList = MutableLiveData<ArrayList<Contact>>().apply {
        userRepository.getMyContact {
            value = it
        }
    }

    val contactsList: LiveData<ArrayList<Contact>>
        get() = _contactsList

}