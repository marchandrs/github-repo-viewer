package com.marchand.githubrepoviewer.ui.home

import android.app.Application
import androidx.lifecycle.*
import com.marchand.githubrepoviewer.model.ReposDataItem
import com.marchand.githubrepoviewer.repositories.RepoRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val repoRepository: RepoRepository = RepoRepository(application.applicationContext)
    private val _repos: MutableLiveData<MutableList<ReposDataItem>> = MutableLiveData()
    val repos: LiveData<MutableList<ReposDataItem>> = _repos
    val tvReposLabelVisible: LiveData<Boolean> = repos.map {
        it.isEmpty()
    }

    fun setRepos(r: MutableList<ReposDataItem>) {
        _repos.value = r
    }

    fun fetchApi(user: String) {
        CoroutineScope(IO).launch {
            _repos.postValue(repoRepository.fetchFromApi(user))
        }
    }
}