package com.example.wildifecare_mobile.service

import com.example.wildifecare_mobile.model.NationalPark
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NationalParkService {

    @GET("/nationalPark/")
    fun getNationalPark(@Query("id") id : Int) : Call<NationalPark>
}