package com.vbuecker.dev_venture_whatsapp.data.repository

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.vbuecker.dev_venture_whatsapp.data.model.Contact
import com.vbuecker.dev_venture_whatsapp.data.model.User

private const val TAG = "UserRepository"
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

    fun getMyContact(onComplete: (ArrayList<Contact>) -> Unit) {
        val current = FirebaseAuth.getInstance().currentUser?.apply {
            if(this.email != null){
                val docRef = db.collection("users")
                    .document(this.email!!)
                    .collection("contacts")
                docRef.get()
                    .addOnSuccessListener { documents ->
                        if (documents != null) {
                            val contacts = ArrayList<Contact>()
                            for (document in documents) {
                                contacts.add(document.toObject(Contact::class.java))
                            }
                            onComplete(contacts)
                        } else {
                            Log.d(TAG, "No contacts found")
                            onComplete(ArrayList<Contact>())
                        }
                    }
                    .addOnFailureListener { exception ->
                        Log.d(TAG, "get contacts failed with ", exception)
                        onComplete(ArrayList<Contact>())
                    }
            } else {
                Log.e(TAG, "Current user has no email")
                onComplete(ArrayList<Contact>())
            }
        }
        Log.e(TAG, "Current user not found")
        onComplete(ArrayList<Contact>())
    }
}