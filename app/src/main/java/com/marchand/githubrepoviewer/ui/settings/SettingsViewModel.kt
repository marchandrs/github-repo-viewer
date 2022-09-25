package com.marchand.githubrepoviewer.ui.settings

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.marchand.githubrepoviewer.R
import com.marchand.githubrepoviewer.repositories.SettingsRepository


class SettingsViewModel(application: Application) : AndroidViewModel(application) {

    private var _user: MutableLiveData<String> = MutableLiveData("")
    var user: LiveData<String> = _user
    var settingsRepository = SettingsRepository(application.getSharedPreferences(application.getString(R.string.shared_preferences_settings), Context.MODE_PRIVATE))

    fun setUser(user: String) {
        _user.value = user
    }

    fun save() {
        settingsRepository.saveUser(user.value!!)
    }

    fun loadUser() {
        setUser(settingsRepository.getUser())
    }

}