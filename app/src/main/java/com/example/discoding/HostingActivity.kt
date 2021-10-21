package com.example.discoding

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import android.widget.ArrayAdapter.createFromResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



class HostingActivity : AppCompatActivity() {

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("http://f022-121-66-18-107.ngrok.io/")
        .build()

    private val service = retrofit.create(HostingService::class.java)

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

//        ArrayAdapter.createFromResource(
//            this,
//            R.array.discording_array,
//            android.R.layout.simple_spinner_item
//        ).also { adapter ->
//            // Specify the layout to use when the list of choices appears
//            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//            // Apply the adapter to the spinner
//            spinner.adapter = adapter
//        }

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

        //서버와 통신
        val hremain_text: TextView = findViewById(R.id.hremain_text)
        val hTime_text: TextView = findViewById(R.id.hTime_text)

        val sharedPreference = getSharedPreferences("UUID", 0)
        val UUID = sharedPreference.getString("UUID", null).toString()

        //userid는 안드로이드 내부에 저장된 고유 값으로 한다.
        service.hotingloading(UUID).enqueue(object : Callback<hotingpagevalue> {
            override fun onResponse(
                call: Call<hotingpagevalue>,
                response: Response<hotingpagevalue>
            ) {
                hTime_text.text = response?.body()?.remaintime.toString()
            }

            override fun onFailure(call: Call<hotingpagevalue>, t: Throwable) {
                Log.e("response", t.toString())
            }
        })

    }


}
