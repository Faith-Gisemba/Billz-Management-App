package com.giseys.assessment.viewModel

import com.giseys.assessment.model.registerResponse
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.giseys.assessment.model.LogInRequest
import com.giseys.assessment.model.LogInResponse
import com.giseys.assessment.model.RegisterRequest
import com.giseys.assessment.repository.UserRepository

class UserViewModel : ViewModel() {
    val userRepo = UserRepository()
    val regiLiveData = MutableLiveData<registerResponse>()
    val logLiveData = MutableLiveData<LogInResponse>()
    val errLiveData = MutableLiveData<String>()

    fun registerUser(registerRequest: RegisterRequest){
        viewModelScope.launch {
            val response = userRepo.register(registerRequest)
            if (response.isSuccessful){
                regiLiveData.postValue(response.body())
            }
            else{
                errLiveData.postValue(response.errorBody()?.string())
            }
        }
    }


    fun logInUser(logInRequest: LogInRequest){
        viewModelScope.launch {
            val response = userRepo.login(logInRequest)
            if (response.isSuccessful){
                logLiveData.postValue(response.body())
            }
            else{
                errLiveData.postValue(response.errorBody()?.string())
            }
        }
    }


}