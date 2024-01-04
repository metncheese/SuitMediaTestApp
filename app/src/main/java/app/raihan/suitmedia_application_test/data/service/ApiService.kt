package app.raihan.suitmedia_application_test.data.service

import android.telecom.Call
import app.raihan.suitmedia_application_test.data.response.ListUserResponse
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ApiService {
    @GET("api/users")
    fun getUsers(
        @QueryMap parameters: HashMap<String, String>
    ): ListUserResponse
}