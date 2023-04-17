package com.colosoft.webservices.ui.mainmenu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.colosoft.webservices.MainActivity
import com.colosoft.webservices.R
import com.colosoft.webservices.databinding.ActivityMainBinding
import com.colosoft.webservices.databinding.ActivityMainMenuBinding
import com.colosoft.webservices.databinding.ActivitySplashBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.*
import kotlin.concurrent.timerTask

class MainMenuActivity : AppCompatActivity() {

    private lateinit var mainMenuActivityBinding: ActivityMainMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Esconder el Toolbar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        supportActionBar?.hide();

        mainMenuActivityBinding = ActivityMainMenuBinding.inflate(layoutInflater)
        val view = mainMenuActivityBinding.root
        setContentView(view)

        mainMenuActivityBinding.mainMenuButton.setOnClickListener {
            goToMainActivity()
        }

    }
    private fun goToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}