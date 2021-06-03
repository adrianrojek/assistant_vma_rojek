package com.rojek.projekt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainMenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        var bundle: Bundle ?=intent.extras
        var loggedUsername = bundle!!.getString("username")
        bundle.putString("username",loggedUsername.toString())
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main_menu)

    }
}