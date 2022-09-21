package com.marchand.githubrepoviewer.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.marchand.githubrepoviewer.api.ApiService
import com.marchand.githubrepoviewer.model.ReposData
import com.marchand.githubrepoviewer.model.ReposDataItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {

    var liveDataList: MutableLiveData<List<ReposDataItem>> = MutableLiveData()

    fun getLiveDataObserver(): MutableLiveData<List<ReposDataItem>> {
        return liveDataList
    }

    fun fetchApi(user: String) {
        Log.d("GITHUB_API", user)
        val retrofitBuilder = ApiService.getApi()

        CoroutineScope(Job() + Dispatchers.IO).launch {
            val retrofitData = retrofitBuilder?.getData(user.toString())
            retrofitData?.enqueue(object : Callback<ReposData?> {
                override fun onResponse(call: Call<ReposData?>, response: Response<ReposData?>) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        Log.d("GITHUB_API", responseBody.toString())
                        liveDataList.postValue(responseBody!!)
                    }
                }

                override fun onFailure(call: Call<ReposData?>, t: Throwable) {
                    //throw t
                    Log.d("GITHUB_API", t.message!!)
                    liveDataList.postValue(listOf())
                }
            })
        }
    }
}