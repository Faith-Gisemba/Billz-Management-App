package Api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
//method is used to set the base URL for all API requests/converting between Java objects and their JSON representation (
//configuring and managing the network-related settings required to make HTTP requests to a remote server.
object ApiClient {
    val retrofit = Retrofit.Builder()
        .baseUrl("http://13.37.106.218/users/register/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    fun <T> buildApiClient(apiInterface: Class<T>): T {
        return retrofit.create(apiInterface)
    }
}
