package com.marchand.githubrepoviewer.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Repo (

    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val description: String,
    val starCount: Int,
    val avatarUrl: String,
    val language: String,
    val htmlUrl: String

)