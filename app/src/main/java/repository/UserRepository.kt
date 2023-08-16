package repository

import Api.ApiClient
import Api.ApiInterface
import model.registerResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import model.LogInRequest
import model.LogInResponse
import model.RegisterRequest
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





