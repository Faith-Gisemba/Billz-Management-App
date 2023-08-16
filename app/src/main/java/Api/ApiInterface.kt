package Api

import model.LogInRequest
import model.LogInResponse
import model.registerResponse
import model.RegisterRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {
    @POST("/users/register")
    suspend fun registerUser(@Body registerRequest: RegisterRequest):Response<registerResponse>

    @POST("/users/login")
    suspend fun loginUser(@Body logInRequest: LogInRequest):Response<LogInResponse>
}