package com.marchand.githubrepoviewer.api

import com.marchand.githubrepoviewer.model.ReposData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {

    /*@GET("users/{user}/repos")
    fun getData(@Path("user") user: String): Call<ReposData>*/

    @GET("users/{user}/repos")
    suspend fun getRepos(@Path("user") user: String): ReposData

}