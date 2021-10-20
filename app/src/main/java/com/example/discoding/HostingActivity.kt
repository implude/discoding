package com.example.discoding

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



class HostingActivity : AppCompatActivity() {

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("http://10.0.2.2:80")
        .build()

    private val service = retrofit.create(HostingService::class.java)

    private var _binding: ActivitMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.hosting)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupSpinnerBotname()
        setupSpinnerHandler()

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

    private fun setupSpinnerBotname(){
        val Botname = resources.getStringArray(R.array.discording_array)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerYear.adapter = adapter
    }

    private fun setupSpinnerHandler(){
        binding.spinnerBotname.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, id: Long) {
                binding.txtBotname.text = "Selected: ${binding.spinnerBotname.getItemAtPosition(position)}"
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }


}

