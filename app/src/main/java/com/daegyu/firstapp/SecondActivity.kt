package com.daegyu.firstapp

import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        val data = intent.getIntExtra("key", 1)
        val image = findViewById<ImageView>(R.id.imageView)
        if(data==1){
            image.setImageResource(R.drawable.cardnews1)
        }
        else if(data==2){
            image.setImageResource(R.drawable.cardnews2)
        }
        else if(data==3){
            image.setImageResource(R.drawable.cardnews3)
        }
        Toast.makeText(this, data.toString(), Toast.LENGTH_SHORT).show()
    }
}