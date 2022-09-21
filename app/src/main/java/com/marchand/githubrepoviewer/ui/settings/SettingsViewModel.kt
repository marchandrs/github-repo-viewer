package com.marchand.githubrepoviewer.ui.settings

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SettingsViewModel: ViewModel() {


    var user: MutableLiveData<String> = MutableLiveData()


    fun save() {
        // usar Room para salvar no banco
        // ou usar sharedPreferences,
        // de qualquer forma, não estava previsto na espeficicação
    }

}