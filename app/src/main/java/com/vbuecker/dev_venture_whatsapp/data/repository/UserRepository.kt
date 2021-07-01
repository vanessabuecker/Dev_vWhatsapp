package com.vbuecker.dev_venture_whatsapp.data.repository

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.vbuecker.dev_venture_whatsapp.data.model.User

object UserRepository {
    private val db by lazy { Firebase.firestore}

    fun addUser(user: User, onSuccess: () -> Unit, onFail: (error: String) -> Unit) {
        db.collection("users")
            .document(user.email)
            .set(user)
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener{ e ->
                onFail(e.localizedMessage)
            }
    }
}