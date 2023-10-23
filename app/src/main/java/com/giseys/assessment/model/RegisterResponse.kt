package com.giseys.assessment.model

import com.google.gson.annotations.Expose

data class registerResponse(
    @Expose var message: String,
    @Expose var user: user,
)
