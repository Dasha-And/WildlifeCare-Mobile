package com.example.wildifecare_mobile.model

import java.io.Serializable;
import java.sql.Date

data class Animal (
    var id: Int,
    var name: String,
    var date_of_birth: Date,
    var species: String,
    var national_park_id: Int
) : Serializable