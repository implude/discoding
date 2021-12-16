package com.example.discoding

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

data class get_from_back(
    val msg: String
)
interface remove_bot {
    @FormUrlEncoded
    @POST("/bot/delete_bot")
    fun delete(
        @Field("name") name: String,
    ): Call<get_from_back>
}