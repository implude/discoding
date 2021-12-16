package com.example.discoding

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

data class post_bot_data(
    val msg: String
)
interface request_from_editbot {
    @FormUrlEncoded
    @POST("/bot/edit_bot")
    fun noget(
        @Field("name") name: String,
        @Field("des") des: String,
        @Field("chname") chname : String,
    ): Call<post_bot_data>
}