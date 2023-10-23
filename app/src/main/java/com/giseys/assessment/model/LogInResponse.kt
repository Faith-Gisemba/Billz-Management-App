package com.giseys.assessment.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LogInResponse(
    @Expose var message: String,
    @Expose @SerializedName ("user_id")var user_Id: String,
    @Expose @SerializedName("access_token") var access_token: String,
)
