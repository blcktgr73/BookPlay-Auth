package com.blacktiger.bookplaytest

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.blacktiger.bookplaytest.databinding.ActivityMainBinding
import com.bumptech.glide.Glide
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient

    companion object {
        private const val TAG = "MainActivity"
    }

    // Google 로그인 결과를 처리하는 ActivityResultLauncher
    private val googleSignInLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        try {
            val account = task.getResult(ApiException::class.java)!!
            Log.d(TAG, "firebaseAuthWithGoogle:" + account.id)
            firebaseAuthWithGoogle(account.idToken!!)
        } catch (e: ApiException) {
            Log.w(TAG, "Google sign in failed", e)
            showError("Google 로그인에 실패했습니다: ${e.message}")
            hideLoginProgress()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Firebase Auth 초기화
        auth = Firebase.auth

        // Google 로그인 설정
        configureGoogleSignIn()

        // UI 이벤트 설정
        setupClickListeners()

        // 현재 로그인 상태 확인
        checkCurrentUser()
    }

    private fun configureGoogleSignIn() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)
    }

    private fun setupClickListeners() {
        binding.googleSignInButton.setOnClickListener {
            signInWithGoogle()
        }

        binding.logoutButton.setOnClickListener {
            signOut()
        }
    }

    private fun checkCurrentUser() {
        val currentUser = auth.currentUser
        if (currentUser != null) {
            showUserSection(currentUser)
        } else {
            showLoginSection()
        }
    }

    private fun signInWithGoogle() {
        showLoginProgress()
        val signInIntent = googleSignInClient.signInIntent
        googleSignInLauncher.launch(signInIntent)
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                hideLoginProgress()
                if (task.isSuccessful) {
                    Log.d(TAG, "signInWithCredential:success")
                    val user = auth.currentUser
                    showSuccess("로그인 성공!")
                    user?.let { showUserSection(it) }
                } else {
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    showError("Firebase 인증에 실패했습니다: ${task.exception?.message}")
                    showLoginSection()
                }
            }
    }

    private fun signOut() {
        showLogoutProgress()
        
        // Firebase 로그아웃
        auth.signOut()
        
        // Google 로그아웃
        googleSignInClient.signOut().addOnCompleteListener(this) {
            hideLogoutProgress()
            showSuccess("로그아웃 되었습니다")
            showLoginSection()
        }
    }

    private fun showUserSection(user: com.google.firebase.auth.FirebaseUser) {
        binding.loginSection.visibility = View.GONE
        binding.userSection.visibility = View.VISIBLE

        // 사용자 정보 표시
        binding.userNameTextView.text = user.displayName ?: "사용자"
        binding.userEmailTextView.text = user.email
        binding.userIdTextView.text = "UID: ${user.uid}"

        // 프로필 이미지 로드
        user.photoUrl?.let { photoUrl ->
            Glide.with(this)
                .load(photoUrl)
                .placeholder(R.drawable.ic_default_profile)
                .error(R.drawable.ic_default_profile)
                .into(binding.profileImageView)
        }
    }

    private fun showLoginSection() {
        binding.loginSection.visibility = View.VISIBLE
        binding.userSection.visibility = View.GONE
    }

    private fun showLoginProgress() {
        binding.googleSignInButton.isEnabled = false
        binding.loginProgressBar.visibility = View.VISIBLE
    }

    private fun hideLoginProgress() {
        binding.googleSignInButton.isEnabled = true
        binding.loginProgressBar.visibility = View.GONE
    }

    private fun showLogoutProgress() {
        binding.logoutButton.isEnabled = false
        binding.logoutProgressBar.visibility = View.VISIBLE
    }

    private fun hideLogoutProgress() {
        binding.logoutButton.isEnabled = true
        binding.logoutProgressBar.visibility = View.GONE
    }

    private fun showSuccess(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}