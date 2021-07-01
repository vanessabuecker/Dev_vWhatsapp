package com.vbuecker.dev_venture_whatsapp.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.vbuecker.dev_venture_whatsapp.User

class UserRepository (user: User) {
    fun addUser(user: User){
        val db by lazy {
            Firebase.firestore
        }

        fun addUser(user: User, onSuccess: () -> Unit,  onFailure: (error: String) -> Unit){
        db.collection("users")
            .document(user.email)
            .set(user)
            .addOnSuccessListener { documentReference ->
            }
            .addOnFailureListener{ e ->
                onFailure(e.localizedMessage)
            }
        }
    }
}