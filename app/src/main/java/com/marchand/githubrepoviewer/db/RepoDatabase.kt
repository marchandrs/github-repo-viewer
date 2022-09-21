package com.marchand.githubrepoviewer.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.marchand.githubrepoviewer.daos.RepoDao
import com.marchand.githubrepoviewer.entities.Repo

@Database(
    entities = [Repo::class],
    version = 1
)
abstract class RepoDatabase: RoomDatabase() {

    abstract val repoDao: RepoDao

    companion object {
        @Volatile
        private var instance: RepoDatabase? = null

        fun getInstance(context: Context): RepoDatabase {
            synchronized(this) {
                return instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    RepoDatabase::class.java,
                    "repo_db"
                ).build().also {
                    instance = it
                }
            }
        }
    }

}