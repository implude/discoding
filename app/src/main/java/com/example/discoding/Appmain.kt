package com.example.discoding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Appmain : AppCompatActivity() {

    var gson= GsonBuilder().setLenient().create()
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://f022-121-66-18-107.ngrok.io/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    private val service = retrofit.create(UserRequest ::class.java) //request.kt 에 존재
    private val service2 = retrofit.create(Request2 ::class.java) //request2.kt 에 존재
    private val service3 = retrofit.create(Request3 ::class.java) //appmainrequest.kt에 존재



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)

        val sharedPreference = getSharedPreferences("UUID", 0)
        val editor = sharedPreference.edit()
        if(sharedPreference.getString("UUID", null).toString() == "null"){
            service2.getuuid().enqueue(object : Callback<uuid> {
                override fun onResponse(
                    call: Call<uuid>,
                    response: Response<uuid>
                ) {
                    editor.putString("UUID", response.body()?.uuid.toString())
                    editor.apply()
                    service.getuserinfo(response.body()?.uuid.toString()).enqueue(object : Callback<MemberResult> {
                        override fun onResponse(
                            call: Call<MemberResult>,
                            response: Response<MemberResult>
                        ) {
                            Log.d("hfd", response.body()?.msg.toString())
                        }
                        override fun onFailure(call: Call<MemberResult>, t: Throwable) {
                            Log.d("result",t.toString())
                        }
                    })
                }
                override fun onFailure(call: Call<uuid>, t: Throwable) {
                    Log.d("result",t.toString())
                }
            })
        }
        else{
            service3.send_uuid(sharedPreference.getString("UUID", null).toString()).enqueue(object : Callback<arrayGet_info> {
                override fun onResponse(
                    call: Call<arrayGet_info>,
                    response: Response<arrayGet_info>
                ) {
                    //리사이클러뷰 하면됌
                    Log.d("body",response.body().toString())
                }
                override fun onFailure(call: Call<arrayGet_info>, t: Throwable) {
                    Log.d("result",t.toString())
                }
            })
        }





        val mHosting_btn = findViewById<android.widget.Button>(R.id.mHosting_btn) //메인페이지에 있는 호스팅 버튼
        val mShare_btn = findViewById<android.widget.Button>(R.id.mShare_btn) //메인페이지에 있는 공유 버튼
        val mPlus_btn = findViewById<android.widget.Button>(R.id.mPlus_btn) //메인페이지에 있는 플러스 버튼

        mHosting_btn.setOnClickListener() { //메인에서 호스팅 페이지로
            val mGo_hosting = Intent(this, HostingActivity::class.java)
            startActivity(mGo_hosting)
        }

        mShare_btn.setOnClickListener() { //메인에서 공유 페이지로
            val mGo_share = Intent(this, Share::class.java)
            startActivity(mGo_share)
        }

        mPlus_btn.setOnClickListener() { //메인에서 봇생성페이지로
            val mGo_CreateBot = Intent(this, CreateBot::class.java)
            startActivity(mGo_CreateBot)
        }




        //리사이클러뷰
        val mainRecycler = findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.mainrecycler)
        val profileList = arrayListOf(
            Profiles("김은교 일해라"),
            Profiles("가자"),
            Profiles("ㄷ자"),
            Profiles("ㄹ자"),
            Profiles("ㅎ자"),
            Profiles("ㅋ자"),
            Profiles("ㅂ자")
        )
        mainRecycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        mainRecycler.setHasFixedSize(true)

        mainRecycler.adapter = ProfileAdapter(profileList)
    }


}