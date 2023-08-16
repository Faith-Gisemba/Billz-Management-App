package model

import com.google.gson.annotations.SerializedName

data class user(
   @SerializedName("phone_number") var phonenumber: String,
    @SerializedName("first_name")var firstname: String,
    @SerializedName("last_name")var lastname: String,
    @SerializedName("user_id")var userId: String,
    var email: String,

)
