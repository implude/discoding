package com.example.discoding

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.blocky.Appmain

class EditBot:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_bot)

        val ebBack_button = findViewById<ImageButton>(R.id.ebBack_button)
        val ebShare_btn = findViewById<ImageView>(R.id.ebShare_btn)
        val ebCoding_btn = findViewById<ImageView>(R.id.ebCoding_btn)

        ebBack_button.setOnClickListener() {
            val ebGo_main = Intent(this, Appmain::class.java)
            startActivity(ebGo_main)
        }
        ebShare_btn.setOnClickListener() {
            val ebGo_share = Intent(this, share::class.java)
            startActivity(ebGo_share)
        }

    }
}