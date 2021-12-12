package com.example.wildifecare_mobile.service

import com.example.wildifecare_mobile.model.Animal
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AnimalService {

    @GET("{nationalParkId}/animals")
    fun getAnimals(@Path("nationalParkId") nationalParkId : Int): Call<ArrayList<Animal>>

    @GET("/get_icon_url")
    fun getIconUrl(@Query("id") id : Int) : Call<String>
}