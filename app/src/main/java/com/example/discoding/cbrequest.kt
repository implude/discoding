/*package com.example.discoding

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Path

interface cbrequest {
    @FormUrlEncoded
    @POST("/bot/create_bot")
    fun getbotinfo(
        @Field("userid") userid: String,
        @Field("botName") botname: String,
        @Field("description") description: String,
        @Field("token") token: String,
        @Field("img_url") img_url: String, //이미지 경로
    ): Call<MemberResult>
}
data class cbresult(
    val
)*/