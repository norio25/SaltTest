package id.norio.salttest.network

import id.norio.salttest.response.NewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Norio on 6/18/2023.
 */
interface ApiService {

    @GET("top-headlines")
    fun getNews(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String
    ): Call<NewsResponse>
}