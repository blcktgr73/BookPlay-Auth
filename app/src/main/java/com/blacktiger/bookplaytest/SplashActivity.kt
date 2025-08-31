package com.blacktiger.bookplaytest

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.blacktiger.bookplaytest.databinding.ActivitySplashBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivitySplashBinding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        checkAuthAndNavigate()
    }
    
    private fun checkAuthAndNavigate() {
        lifecycleScope.launch {
            // 스플래시 화면을 최소 1.5초 보여주기
            delay(1500)
            
            if (AuthManager.isUserLoggedIn()) {
                // 로그인된 사용자 -> BookListActivity로 이동
                startActivity(Intent(this@SplashActivity, BookListActivity::class.java))
            } else {
                // 미로그인 사용자 -> LoginActivity로 이동
                startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
            }
            
            finish()
        }
    }
}