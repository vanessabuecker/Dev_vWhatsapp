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

    fun myEmail(): String {
        return firebaseAuth.currentUser?.email ?: ""
    }

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
            if (this.email != null)
                db.collection("users").document(this.email!!).collection("contacts")
                    .addSnapshotListener { data, error ->
                        if (error != null) {
                            Log.e(TAG, error.localizedMessage)
                        } else {
                            if (data != null) {
                                val contacts = data.toObjects(Contact::class.java)
                                onComplete(contacts as ArrayList<Contact>)
                            }
                        }
                    }
            else
                onComplete(ArrayList())
        }
    }
}