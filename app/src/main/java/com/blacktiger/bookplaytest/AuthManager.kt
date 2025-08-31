package com.blacktiger.bookplaytest

import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

object AuthManager {
    
    fun getCurrentUser(): FirebaseUser? {
        return FirebaseAuth.getInstance().currentUser
    }
    
    fun isUserLoggedIn(): Boolean {
        return getCurrentUser() != null
    }
    
    fun getGoogleSignInClient(context: Context): GoogleSignInClient {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(context.getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        
        return GoogleSignIn.getClient(context, gso)
    }
    
    suspend fun signOut(context: Context) {
        // Firebase 로그아웃
        FirebaseAuth.getInstance().signOut()
        
        // Google 로그아웃
        getGoogleSignInClient(context).signOut()
    }
}