package com.example.wildifecare_mobile.model

import java.io.Serializable

data class NationalPark (
    var id: Int,
    var name: String,
    var country: String,
    var region: String,
    var latitude: Double,
    var longitude: Double
) : Serializable
