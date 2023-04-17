package com.colosoft.webservices.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import com.colosoft.webservices.MainActivity
import com.colosoft.webservices.databinding.ActivitySplashBinding
import com.colosoft.webservices.ui.mainmenu.MainMenuActivity
import java.util.*
import kotlin.concurrent.timerTask

class SplashActivity : AppCompatActivity() {

    private lateinit var splashBinding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Esconder el Toolbar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        supportActionBar?.hide();

        splashBinding = ActivitySplashBinding.inflate(layoutInflater)
        val view = splashBinding.root
        setContentView(view)
        val timer = Timer()
        timer.schedule(
            timerTask {
                goToMainMenuActivity()
            }, 2000
        )
    }
    private fun goToMainMenuActivity() {
        val intent = Intent(this, MainMenuActivity::class.java)
        startActivity(intent)
        finish()
    }
}