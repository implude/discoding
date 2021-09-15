package com.example.blocky

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.discoding.R

class createBot : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_bot)

        val gotoback = findViewById<TextView>(R.id.gotoback)

        gotoback.setOnClickListener {
            val gotomain = Intent(this, Appmain::class.java)
            startActivity(gotomain)
        }

    }
}