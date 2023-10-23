package com.giseys.assessment.repository

import com.giseys.assessment.Api.ApiClient
import com.giseys.assessment.Api.ApiInterface
import com.giseys.assessment.model.registerResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.giseys.assessment.model.LogInRequest
import com.giseys.assessment.model.LogInResponse
import com.giseys.assessment.model.RegisterRequest
import retrofit2.Response

class UserRepository {
    val apiClient = ApiClient.buildApiClient(ApiInterface::class.java)

    suspend fun register(registerRequest: RegisterRequest):Response<registerResponse>{
        return withContext(Dispatchers.IO){
            apiClient.registerUser(registerRequest)
        }
    }
    suspend fun login(logInRequest: LogInRequest):Response<LogInResponse>{
        return withContext(Dispatchers.IO){
            apiClient.loginUser(logInRequest)
        }
    }
}





