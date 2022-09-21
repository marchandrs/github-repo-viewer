package com.marchand.githubrepoviewer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout

class SplashScreenActivity : AppCompatActivity() {

    lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val ivIcon = findViewById<ImageView>(R.id.iv_icon)
        ivIcon.animate().scaleY(1.15f).duration = 500
        ivIcon.animate().scaleX(1.15f).duration = 500

        lifecycleScope.launch {
            delay(1000)
            startActivity(Intent(this@SplashScreenActivity,MainActivity::class.java))
            finish()
        }
    }
}