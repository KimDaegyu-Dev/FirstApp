package com.daegyu.firstapp

import BookAdapter
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.daegyu.firstapp.api.RetrofitClient
import com.daegyu.firstapp.databinding.ActivityBookListBinding
import com.daegyu.firstapp.model.BookResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BookListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBookListBinding
    private val bookAdapter = BookAdapter()
    private val retrofitClient = RetrofitClient.bookApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recyclerView.adapter = bookAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.btnSearch.setOnClickListener {
            val keyword = binding.inputKeyword.text.toString()
            retrofitClient.search(keyword).enqueue(object:Callback<BookResponse>{
                // 정상적인 요청
                override fun onResponse(
                    call: Call<BookResponse>,
                    response: Response<BookResponse>
                ) {
                    if(response.isSuccessful) { // 요청 성공
                        val bookList = response.body()!!.item!!
                        Log.d(".BookListActivity", "onResponse: ${bookList}")
                        bookAdapter.setList(bookList)
                    } else {
                        // 요청 실패
                    }
                }

                // 오류
                override fun onFailure(call: Call<BookResponse>, t: Throwable) {

                }
            })



        }
    }
}