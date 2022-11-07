package com.tengizMKCorp.tengizExpress.firebase

import android.net.Uri
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FileDownloadTask
import com.google.firebase.storage.FirebaseStorage
import com.tengizMKCorp.tengizExpress.ui.element.model.User
import java.io.File

object Firebase {
    private val firebaseAuth = FirebaseAuth.getInstance()

    val getCurrentUser = firebaseAuth.currentUser

    fun signIn(email: String, password: String) =
        firebaseAuth.signInWithEmailAndPassword(email, password)

    fun signUp(email: String, password: String) =
        firebaseAuth.createUserWithEmailAndPassword(email, password)


    fun resetPassword(email: String) = firebaseAuth.sendPasswordResetEmail(email)

    fun emailVerificationStatus() = firebaseAuth.currentUser?.isEmailVerified

    fun sendEmailVerification() = firebaseAuth.currentUser?.sendEmailVerification()

    fun signOut() = firebaseAuth.signOut()

}