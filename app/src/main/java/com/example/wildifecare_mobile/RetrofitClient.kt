package com.example.wildifecare_mobile

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

object RetrofitClient {
    private var retrofit: Retrofit? = null
    val gson = GsonBuilder().setDateFormat("yyyy-MM-dd").create()


    fun getClient(): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl("https://wildlife-care-backend.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }
        return retrofit!!
    }
}