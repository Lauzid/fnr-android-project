package com.example.fnr_android_project.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Network {

    private const val BASE_URL = "https://api.scryfall.com"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api: Api = retrofit.create(Api::class.java)

}