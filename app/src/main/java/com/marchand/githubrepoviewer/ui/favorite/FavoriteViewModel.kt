package com.marchand.githubrepoviewer.ui.favorite

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.marchand.githubrepoviewer.entities.Repo

class FavoriteViewModel : ViewModel() {
    var favReposList: MutableLiveData<List<Repo>> = MutableLiveData()
}