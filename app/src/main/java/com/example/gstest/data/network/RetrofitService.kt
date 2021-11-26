package RetrofitService.data.network

import com.google.gson.JsonObject
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitService {

    @GET("list/1?page=1&api_key=0f6b3d83c971b0eb68c358686b18fe6f")
    suspend fun getCharecter(): JsonObject

}