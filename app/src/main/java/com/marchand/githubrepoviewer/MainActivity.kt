package com.marchand.githubrepoviewer

import android.content.res.Resources
import android.os.Bundle
import android.provider.Settings
import android.text.SpannableStringBuilder
import androidx.activity.viewModels
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.text.bold
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.marchand.githubrepoviewer.databinding.ActivityMainBinding
import com.marchand.githubrepoviewer.ui.settings.SettingsFragment
import com.marchand.githubrepoviewer.ui.settings.SettingsViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val settingsFragment = SettingsFragment()
    private val settingsViewModel: SettingsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupBottomNavigationView()
        setupHeader()
        settingsViewModel.loadUser()
    }

    private fun setupBottomNavigationView() {
        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        navView.setupWithNavController(navController)
    }

    private fun setupHeader() {
        val appTitle = SpannableStringBuilder()
            .bold { append("We") }
            .append("Fit")
        binding.appTitle.text = appTitle

        binding.ivSettings.setOnClickListener {
            settingsFragment.show(supportFragmentManager, "SettingsFragment")
        }
    }
}