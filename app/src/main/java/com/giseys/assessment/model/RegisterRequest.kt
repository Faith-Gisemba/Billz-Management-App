package com.giseys.assessment.model
//e data structure for making a registration request to some API endpoint

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RegisterRequest(
    @SerializedName("first_name") var first_name : String,
    @SerializedName("last_name") var last_name : String,
    @Expose var email : String,
    @Expose var password : String,
    @SerializedName("phone_number") var phone_number : String,
)
