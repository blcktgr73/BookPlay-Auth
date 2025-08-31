package com.blacktiger.bookplaytest

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.blacktiger.bookplaytest.databinding.ActivityBookListBinding
import com.bumptech.glide.Glide

class BookListActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityBookListBinding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setupToolbar()
        setupWelcomeSection()
        setupRecyclerView()
    }
    
    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "내 도서목록"
    }
    
    private fun setupWelcomeSection() {
        val user = AuthManager.getCurrentUser()
        user?.let {
            binding.welcomeTextView.text = "${it.displayName}님, 안녕하세요!"
            
            // 프로필 이미지 로드
            it.photoUrl?.let { photoUrl ->
                Glide.with(this)
                    .load(photoUrl)
                    .placeholder(R.drawable.profile_default)
                    .error(R.drawable.profile_default)
                    .circleCrop()
                    .into(binding.profileImageView)
            }
        }
    }
    
    private fun setupRecyclerView() {
        binding.booksRecyclerView.layoutManager = LinearLayoutManager(this)
        
        // 임시 데이터
        val sampleBooks = listOf(
            "Clean Code",
            "Effective Java",
            "Android Programming",
            "Kotlin in Action",
            "Design Patterns"
        )
        
        binding.booksRecyclerView.adapter = BookListAdapter(sampleBooks)
    }
    
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }
    
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_profile -> {
                startActivity(Intent(this, ProfileActivity::class.java))
                true
            }
            R.id.action_settings -> {
                startActivity(Intent(this, ProfileActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}