package com.vbuecker.dev_venture_whatsapp.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.vbuecker.dev_venture_whatsapp.data.model.Contact
import com.vbuecker.dev_venture_whatsapp.data.model.User

private const val TAG = "UserRepository"

object UserRepository {
    private val firebaseAuth = FirebaseAuth.getInstance()
    private val db by lazy { Firebase.firestore }

    fun checkIfUserIsAuthenticated(): MutableLiveData<Boolean> {
        val isUserAuthenticated = MutableLiveData<Boolean>()
        val firebaseUser = firebaseAuth.currentUser
        isUserAuthenticated.value = firebaseUser != null
        return isUserAuthenticated
    }

    fun saveUser() {
        val firebaseUser = firebaseAuth.currentUser
        val user = User(
            firebaseUser?.displayName ?: "No name",
            firebaseUser?.email ?: "No email",
            firebaseUser!!.uid
        )
        db.collection("users")
            .document(user.email)
            .set(user)
            /*TODO: Adicionar mensagem de sucesso e erro */
            .addOnSuccessListener { Log.d(TAG, "User salvo") }
            .addOnFailureListener { e -> Log.d(TAG, "Falha ao salvar ${e.localizedMessage} salvo") }
    }

    fun getMyContact(onComplete: (ArrayList<Contact>) -> Unit) {
        FirebaseAuth.getInstance().currentUser?.apply {
            if (this.email != null) {
                db.collection("users").document("contacts").collection("contacts")
                    .get()
                    .addOnSuccessListener { documents ->
                        val contacts = ArrayList<Contact>()
                        if (documents != null)
                            for (doc in documents) {
                                contacts.add(
                                    Contact(
                                        name = doc.data.getValue("name") as String,
                                        email = doc.data.getValue("email") as String
                                    )
                                )
                                onComplete(contacts)
                            }
                        else
                            onComplete(ArrayList<Contact>())
                    }
                    .addOnFailureListener {
                        Log.d(TAG, "Falha")
                        onComplete(ArrayList<Contact>())
                    }
            }else{
                onComplete(ArrayList())
            }
        }
    }

//    fun getMyContact(onComplete: (ArrayList<Contact>) -> Unit) {
//        val current = FirebaseAuth.getInstance().currentUser?.apply {
//            if (this.email != null) {
//                try {
//
//                    val docRef = db.collection("users")
//                        .document(this.email!!)
//                        .collection("contacts")
//                    docRef.get()
//                        .addOnSuccessListener { documents ->
//                            if (documents != null) {
//                                val contacts = ArrayList<Contact>()
//                                for (document in documents) {
//                                    contacts.add(
//                                        Contact(
//                                            name = document.data.getValue("name") as String,
//                                            email = document.data.getValue("email") as String
//                                        )
//                                    )
//                                }
//                                onComplete(contacts)
//                            } else {
//                                Log.d(TAG, "No contacts found")
//                                onComplete(ArrayList<Contact>())
//                            }
//                        }
//                        .addOnFailureListener { exception ->
//                            Log.d(TAG, "get contacts failed with ", exception)
//                            onComplete(ArrayList<Contact>())
//                        }
//                } catch (e: Exception) {
//                    e.printStackTrace()
//                }
//
//            } else {
//                Log.e(TAG, "Current user has no email")
//                onComplete(ArrayList<Contact>())
//            }
//        }
//        Log.e(TAG, "Current user not found")
//        onComplete(ArrayList<Contact>())
//    }
}