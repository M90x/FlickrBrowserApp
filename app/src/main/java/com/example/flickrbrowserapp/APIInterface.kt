package com.example.flickrbrowserapp

import retrofit2.http.GET
import retrofit2.http.Query

interface APIInterface {


    @GET("rest/")

    fun getPhotoAPI(@Query("method") method:String,
                    @Query("api_key") apiKey:String,
                    @Query("tags") tags:String,
                    @Query("per_page") perPage:String,
                    @Query ("format") format: String,
                    @Query ("nojsoncallback") callback: String):retrofit2.Call<Photos>
}