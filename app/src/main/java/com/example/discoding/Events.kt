package com.example.discoding

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.blocky.Appmain

class Events:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.events)

        val eBack_button = findViewById<ImageButton>(R.id.eBack_button)

        eBack_button.setOnClickListener() {
            val eGo_main = Intent(this, Appmain::class.java)
            startActivity(eGo_main)
        }
    }
}