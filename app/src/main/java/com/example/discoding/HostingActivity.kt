package com.example.discording_hostingpage

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.example.discoding.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_bot)

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