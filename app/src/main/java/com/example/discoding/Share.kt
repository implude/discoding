package com.example.discoding

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.blocky.Appmain

class Share:AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.share)

        val sMain_btn = findViewById<android.widget.Button>(R.id.sMain_btn)
        val sHosting_btn = findViewById<android.widget.Button>(R.id.sHosting_btn)

        sMain_btn.setOnClickListener() {
            val sGo_main = Intent(this, Appmain::class.java)
            startActivity(sGo_main)
        }
        sHosting_btn.setOnClickListener() {
            val sGo_hosting = Intent(this, HostingActivity::class.java)
            startActivity(sGo_hosting)
        }
    }
}