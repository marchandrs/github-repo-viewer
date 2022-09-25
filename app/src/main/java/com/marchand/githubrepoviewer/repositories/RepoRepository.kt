package com.marchand.githubrepoviewer.repositories

import android.content.Context
import android.util.Log
import com.marchand.githubrepoviewer.api.ApiService
import com.marchand.githubrepoviewer.daos.RepoDao
import com.marchand.githubrepoviewer.db.RepoDatabase
import com.marchand.githubrepoviewer.entities.Repo
import com.marchand.githubrepoviewer.model.ReposData

class RepoRepository(context: Context) {
    private val repoDao: RepoDao = RepoDatabase.getInstance(context).repoDao

    suspend fun insertFavRepo(repo: Repo) {
        repoDao.insertRepo(repo)
    }
     suspend fun getFavRepos(): List<Repo> {
        return repoDao.getRepos()
     }

    suspend fun fetchFromApi(user: String): ReposData? {
        if (user.isBlank()) {
            return ReposData()
        }
        Log.d("GITHUB_API", user)
        val apiService = ApiService.getApi()
        return apiService?.getRepos(user)
    }
}