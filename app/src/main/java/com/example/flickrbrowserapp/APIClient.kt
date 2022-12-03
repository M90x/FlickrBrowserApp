package com.example.flickrbrowserapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIClient {
    var retrofit: Retrofit? = null


    fun getClient(): Retrofit?{

        retrofit = Retrofit.Builder()
            .baseUrl("https://www.flickr.com/services/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit

    }
}