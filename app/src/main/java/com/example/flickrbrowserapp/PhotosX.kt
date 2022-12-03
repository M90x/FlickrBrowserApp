package com.example.flickrbrowserapp

data class PhotosX(
    val page: Int,
    val pages: String,
    val perpage: Int,
    val photo: List<Photo>,
    val total: String
)