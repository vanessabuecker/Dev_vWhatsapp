package com.vbuecker.dev_venture_whatsapp.data.repository

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.vbuecker.dev_venture_whatsapp.data.model.Message
import java.util.*
import kotlin.collections.ArrayList

private const val TAG = "ChatRepository"

object ChatRepository {
    private val firebaseAuth = FirebaseAuth.getInstance()
    private val db by lazy { Firebase.firestore }
    private val currentUserEmail by lazy { UserRepository.myEmail() }

    fun getMessages(
        chatId: String,
        onComplete: (ArrayList<Message>) -> Unit
    ) {
        FirebaseAuth.getInstance().currentUser?.apply {
            db.collection("chats")
                .document(chatId)
                .collection("messages").addSnapshotListener { snapshot, e ->
                    if (e != null) {
                        onComplete(ArrayList())
                        Log.e(TAG, e.localizedMessage)
                        return@addSnapshotListener
                    }

                    var messages = ArrayList<Message>()
                    if (snapshot != null) {
                        messages = snapshot.toObjects(Message::class.java) as ArrayList<Message>
                        messages.sortBy { it.time }
                        onComplete(messages)
                    } else {
                        Log.d(TAG, "snapshot is null")
                    }
                }
        }
    }

    fun getChatWith(contactEmail: String, onComplete: (chatId: String, e: String?) -> Unit) {
        val chat = createChatId(currentUserEmail, contactEmail)
        db.collection("chats")
            .document(chat)
            .get()
            .addOnSuccessListener {
                onComplete(it.id, null)
            }
            .addOnFailureListener {
                onComplete("", it.localizedMessage)
            }
    }

    fun createChatId(email: String, email2: String): String {
        val chatId =
            if (email > email2) "${email}-${email2}" else "${email2}-${email}"
        return chatId
    }

    fun addMessageToChat(chatId: String, from: String, message: String) {
        val data = hashMapOf(
            "from" to from,
            "message" to message,
            "time" to Date().time
        )
        db.collection("chats").document(chatId).collection("messages").document().set(data)
    }
}