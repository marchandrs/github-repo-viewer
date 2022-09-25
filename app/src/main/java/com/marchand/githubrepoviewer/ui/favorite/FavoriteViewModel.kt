package com.marchand.githubrepoviewer.ui.favorite

import android.app.Application
import androidx.lifecycle.*
import com.marchand.githubrepoviewer.entities.Repo
import com.marchand.githubrepoviewer.repositories.RepoRepository

class FavoriteViewModel(application: Application) : AndroidViewModel(application) {
    private var repoRepository: RepoRepository = RepoRepository(application.applicationContext)
    private val _favRepos: MutableLiveData<List<Repo>> = MutableLiveData()
    val favRepos: LiveData<List<Repo>> = _favRepos
    val tvFavRepoLabelVisible: LiveData<Boolean> = favRepos.map {
        it.isEmpty()
    }

    fun setFavRepos(favRepos: List<Repo>) {
        _favRepos.value = favRepos
    }

    suspend fun loadFavRepos() {
        setFavRepos(repoRepository.getFavRepos())
    }
}