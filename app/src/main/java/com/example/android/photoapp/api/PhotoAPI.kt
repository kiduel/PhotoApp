package com.example.android.photoapp.api

import com.example.android.photoapp.models.PhotoList
import retrofit2.Call
import retrofit2.http.GET

interface PhotoAPI {
    @GET("?key=11720573-21ca0e66bb74a065b6ece745d&q=animals&image_type=photo")
    fun getPhotos(): Call<PhotoList>
}