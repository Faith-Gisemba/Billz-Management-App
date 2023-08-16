package model

data class LogInResponse(
    var message: String,
    var user_id: String,
    var access_token: String,
)
