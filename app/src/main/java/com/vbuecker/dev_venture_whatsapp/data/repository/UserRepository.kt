package com.vbuecker.dev_venture_whatsapp.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.vbuecker.dev_venture_whatsapp.data.model.User

private const val TAG = "UserRepository"

object UserRepository {
    private val firebaseAuth = FirebaseAuth.getInstance()
    private val db by lazy { Firebase.firestore }

    fun addUser(user: User, onSuccess: () -> Unit, onFail: (error: String) -> Unit) {
        db.collection("users")
            .document(user.email)
            .set(user)
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener { e ->
                onFail(e.localizedMessage)
            }
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
}