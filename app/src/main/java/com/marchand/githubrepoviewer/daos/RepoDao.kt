package com.marchand.githubrepoviewer.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.marchand.githubrepoviewer.entities.Repo

@Dao
interface RepoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRepo(repo: Repo)

    @Query("SELECT * FROM repo")
    suspend fun getRepos(): List<Repo>

}