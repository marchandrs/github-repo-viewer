package com.marchand.githubrepoviewer.ui.settings

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.marchand.githubrepoviewer.R

class SettingsFragment: BottomSheetDialogFragment() {

    private val settingsViewModel: SettingsViewModel by activityViewModels()
    private lateinit var edtUser: EditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.change_user_bottom_sheet_modal, container, false)
        setupObserver(view)
        setupUiListeners(view)
        return view
    }

    private fun setupObserver(view: View) {
        edtUser = view.findViewById(R.id.edt_user_settings)
        settingsViewModel.user.observe(viewLifecycleOwner) {
            edtUser.setText(it)
        }
    }

    private fun setupUiListeners(view: View) {
        val btnClose = view.findViewById<Button>(R.id.btn_close)
        btnClose.setOnClickListener {
            dismiss()
        }

        val btnSave = view.findViewById<Button>(R.id.btn_save)
        btnSave.setOnClickListener {
            settingsViewModel.setUser(edtUser.text.toString())
            settingsViewModel.save()
            dismiss()
        }
    }
}