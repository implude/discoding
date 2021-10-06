package com.example.discoding

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface CreateBotData {
    @FormUrlEncoded
    @POST("/bot/create_bot")
    fun getbotinfo (
        @Field("botName") botName: String,
        @Field("description") description: String
    ): Call<createBotResult>
}
data class createBotResult(
    //여기 하는 중
    val val1: String,
    val val2: String,
    val val3: String
)