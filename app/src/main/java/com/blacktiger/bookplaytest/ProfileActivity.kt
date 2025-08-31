package com.blacktiger.bookplaytest

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.blacktiger.bookplaytest.databinding.ActivityProfileBinding
import com.bumptech.glide.Glide
import kotlinx.coroutines.launch

class ProfileActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityProfileBinding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setupToolbar()
        setupUserInfo()
        setupClickListeners()
    }
    
    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "프로필"
            setDisplayHomeAsUpEnabled(true)
        }
    }
    
    private fun setupUserInfo() {
        val user = AuthManager.getCurrentUser()
        user?.let {
            binding.userNameTextView.text = it.displayName ?: "사용자"
            binding.userEmailTextView.text = it.email
            binding.userIdTextView.text = "UID: ${it.uid}"
            binding.creationTimeTextView.text = "가입일: ${
                java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault())
                    .format(java.util.Date(it.metadata?.creationTimestamp ?: 0))
            }"
            binding.lastSignInTextView.text = "최근 로그인: ${
                java.text.SimpleDateFormat("yyyy-MM-dd HH:mm", java.util.Locale.getDefault())
                    .format(java.util.Date(it.metadata?.lastSignInTimestamp ?: 0))
            }"

            // 프로필 이미지 로드
            it.photoUrl?.let { photoUrl ->
                Glide.with(this)
                    .load(photoUrl)
                    .placeholder(R.drawable.profile_default)
                    .error(R.drawable.profile_default)
                    .into(binding.profileImageView)
            }
        }
    }
    
    private fun setupClickListeners() {
        binding.logoutButton.setOnClickListener {
            showLogoutConfirmation()
        }
    }
    
    private fun showLogoutConfirmation() {
        AlertDialog.Builder(this)
            .setTitle("로그아웃")
            .setMessage("정말 로그아웃하시겠습니까?")
            .setPositiveButton("로그아웃") { _, _ ->
                performLogout()
            }
            .setNegativeButton("취소", null)
            .show()
    }
    
    private fun performLogout() {
        showLogoutProgress()
        
        lifecycleScope.launch {
            try {
                AuthManager.signOut(this@ProfileActivity)
                navigateToLogin()
            } catch (e: Exception) {
                hideLogoutProgress()
                // 에러 처리 (Toast 등)
            }
        }
    }
    
    private fun navigateToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
    
    private fun showLogoutProgress() {
        binding.logoutButton.isEnabled = false
        binding.logoutProgressBar.visibility = View.VISIBLE
    }
    
    private fun hideLogoutProgress() {
        binding.logoutButton.isEnabled = true
        binding.logoutProgressBar.visibility = View.GONE
    }
    
    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}