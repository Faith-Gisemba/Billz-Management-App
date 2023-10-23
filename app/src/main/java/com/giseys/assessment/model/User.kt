package com.giseys.assessment.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class user(
    @Expose @SerializedName("phone_number") var phonenumber: String,
    @Expose @SerializedName("first_name") var firstname: String,
    @Expose @SerializedName("last_name") var lastname: String,
    @Expose @SerializedName("user_Id") var userId: String,
    @Expose var email: String,

    )
