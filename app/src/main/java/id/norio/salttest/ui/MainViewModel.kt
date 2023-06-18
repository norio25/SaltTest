package id.norio.salttest.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.norio.salttest.network.ApiConfig
import id.norio.salttest.response.NewsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Norio on 6/18/2023.
 */
class MainViewModel : ViewModel() {

    private val _newsData = MutableLiveData<NewsResponse>()
    val newsData: LiveData<NewsResponse> = _newsData

    init {
        getNews()
    }

    private fun getNews() {
        val client = ApiConfig.getApiService().getNews("us", "183fa9671e414478851963323ddaadf5")
        client.enqueue(object : Callback<NewsResponse> {
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                if (response.isSuccessful) {
                    _newsData.value = response.body()
                } else {
                    Log.e("News Data", "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                Log.e("News Data", "onFailure: ${t.message.toString()}")
            }

        })
    }
}