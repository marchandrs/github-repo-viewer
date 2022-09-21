package com.marchand.githubrepoviewer.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.activityViewModels
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
        edtUser = view.findViewById<EditText>(R.id.edt_user_settings)
        settingsViewModel.user.observe(viewLifecycleOwner) {
            edtUser.setText(it)
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnClose = view.findViewById<Button>(R.id.btn_close)
        btnClose.setOnClickListener {
            dismiss()
        }

        val btnSave = view.findViewById<Button>(R.id.btn_save)
        btnSave.setOnClickListener {
            settingsViewModel.user.postValue(edtUser.text.toString())
            settingsViewModel.save()
        }
    }

}