package com.giseys.assessment.Api
//API endpoints for making network requests using the Retrofit library.
//meant to be called from a coroutine. allows network calls to be executed on a background thread without blocking the main thread.
import com.giseys.assessment.model.Bill
import com.giseys.assessment.model.LogInRequest
import com.giseys.assessment.model.LogInResponse
import com.giseys.assessment.model.registerResponse
import com.giseys.assessment.model.RegisterRequest
import com.giseys.assessment.model.upcomingBill
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiInterface {
    @POST("/users/register")
    suspend fun registerUser(@Body registerRequest: RegisterRequest):Response<registerResponse>

    @POST("/users/login")
    suspend fun loginUser(@Body logInRequest: LogInRequest):Response<LogInResponse>

    @POST("/bills")
    suspend fun postBill(@Header("Authorization") token: String, @Body bill: Bill): Response<Bill>

    @POST("/upcoming-bills")
    suspend fun postUpcomingBills(@Header("Authorization") token: String, @Body upcomingBill: upcomingBill): Response<upcomingBill>

    @GET("/bills")
    suspend fun fetchRemoteBills(@Header("Authorization") token: String): Response<List<Bill>>

    @GET("/upcoming-bills")
    suspend fun fetchRemoteUpcomingBills(@Header("Authorization") token: String): Response<List<upcomingBill>>

}
