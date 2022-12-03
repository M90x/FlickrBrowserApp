package com.example.flickrbrowserapp

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


class MainActivity : AppCompatActivity() {

    var photosList = arrayListOf<Photo>()
    lateinit var rvAdapter : RecyclerViewAdapter
    private lateinit var myRV : RecyclerView

    private lateinit var searchInput : EditText
    private lateinit var searchBtn : Button

    var tags = ""
    

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myRV = findViewById(R.id.rvPhotos)
        searchInput = findViewById(R.id.searchField)
        searchBtn = findViewById(R.id.searchBtn)

        searchBtn.setOnClickListener {

            if (searchInput.text.isNotEmpty()){
                tags = searchInput.text.toString()
                Log.d("test", "tags values are : $tags")
                getPhotos()
            }
            else {
                Toast.makeText(this,"Please enter the tag of photo", Toast.LENGTH_LONG).show()

            }
        }


        //Send array to recycler view adapter
        rvAdapter = RecyclerViewAdapter(photosList)
        myRV.adapter = rvAdapter
        myRV.layoutManager = LinearLayoutManager(this)

    }

    private fun getPhotos(){

        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)

        val method = "flickr.photos.search"
        val apiKey = "b6c63bb6870fee1d8cb161034d91d2af"
        val perPage = "20"
        val format = "json"
        val callback = "1"

        apiInterface?.getPhotoAPI(method, apiKey, tags, perPage, format, callback)
            ?.enqueue(object : Callback<Photos> {
                @RequiresApi(Build.VERSION_CODES.N)

                //onResponse function
                override fun onResponse(
                    call: Call<Photos>,
                    response: Response<Photos>
                ) {

                    val body = response.body()

                    if (body != null)
                    {
                        val photosX = body.photos
                        val photoList = photosX.photo

                        photosList.addAll(photoList)
                        rvAdapter.update(photosList)

                    }

                }

                //onFailure function
                override fun onFailure(call: Call<Photos>, t: Throwable) {
                    Log.d("retrofit", "onFailure: ${t.message.toString()}")
                }

            })
    }
}