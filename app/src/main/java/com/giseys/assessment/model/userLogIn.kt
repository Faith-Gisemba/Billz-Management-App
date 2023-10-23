package com.giseys.assessment.model

import android.provider.ContactsContract.CommonDataKinds.Email
import com.google.gson.annotations.Expose

data class userLogIn(
    @Expose var email: String,
    @Expose var password : String
)
