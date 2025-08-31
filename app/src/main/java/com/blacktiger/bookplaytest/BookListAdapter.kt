package com.blacktiger.bookplaytest

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.blacktiger.bookplaytest.databinding.ItemBookBinding

class BookListAdapter(
    private val books: List<String>
) : RecyclerView.Adapter<BookListAdapter.BookViewHolder>() {

    inner class BookViewHolder(private val binding: ItemBookBinding) : 
        RecyclerView.ViewHolder(binding.root) {
        
        fun bind(bookTitle: String) {
            binding.bookTitleTextView.text = bookTitle
            binding.bookAuthorTextView.text = "저자 미상" // 임시 데이터
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val binding = ItemBookBinding.inflate(
            LayoutInflater.from(parent.context), 
            parent, 
            false
        )
        return BookViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bind(books[position])
    }

    override fun getItemCount(): Int = books.size
}