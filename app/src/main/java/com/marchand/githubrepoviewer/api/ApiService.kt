package com.marchand.githubrepoviewer.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiService {

    // example url:
    // https://api.github.com/users/facebook/repos
    private const val BASE_URL = "https://api.github.com/"

    private var api: ApiInterface? = null

    fun getApi(): ApiInterface? {
        if (api == null) {
            api = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
                .create(ApiInterface::class.java)
        }
        return api
    }

}