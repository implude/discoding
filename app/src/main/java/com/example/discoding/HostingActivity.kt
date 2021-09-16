package com.example.discoding

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner

class HostingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.hosting)

        val hMain_btn = findViewById<android.widget.Button>(R.id.hMain_btn)
        val hShare_btn = findViewById<android.widget.Button>(R.id.hShare_btn)
        hMain_btn.setOnClickListener() {
            val hGo_main = Intent(this, Appmain::class.java)
            startActivity(hGo_main)
        }
        hShare_btn.setOnClickListener() {
            val hGo_share = Intent(this, hShare_btn::class.java)
            startActivity(hGo_share)
        }

        //spinner
        val spinner: Spinner = findViewById(R.id.choose_spinner)

        ArrayAdapter.createFromResource(
            this,
            R.array.discording_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }

        class SpinnerActivity : Activity(), AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
                // An item was selected. You can retrieve the selected item using
                // parent.getItemAtPosition(pos)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Another interface callback
            }
        }

        val items = resources.getStringArray(R.array.discording_array)
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items)
        spinner.adapter = adapter
    }
}