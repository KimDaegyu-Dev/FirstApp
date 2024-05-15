package com.daegyu.firstapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.daegyu.firstapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bookBtn =findViewById<Button>(R.id.bookActivityBtn)

        val image1 = findViewById<ImageView>(R.id.image1)
        val image2 = findViewById<ImageView>(R.id.image2)
        val image3 = findViewById<ImageView>(R.id.image3)
        binding.bookActivityBtn.setOnClickListener {
            val intent = Intent(this, BookListActivity::class.java)
            startActivity(intent)
        }
        image1.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("key", 1)
            startActivity(intent)
        }
        image2.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("key", 2)
            startActivity(intent)
        }
        image3.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("key", 3)
            startActivity(intent)
        }
    }
}