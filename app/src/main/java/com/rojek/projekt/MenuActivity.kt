package com.rojek.projekt

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_menu.*
import kotlinx.android.synthetic.main.fragment_menu.*
import kotlin.math.log

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?)  {
        var bundle: Bundle ?=intent.extras
        var username = bundle!!.getString("username")

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        var notesButton = findViewById(R.id.notesButton) as Button
        var inventoryButton = findViewById(R.id.inventoryButton) as Button
        var logout = findViewById(R.id.logoutbutton) as Button
        var weatherButton = findViewById(R.id.cloudButton) as ImageButton
        userText.setText("USER")


        weatherButton.setOnClickListener {
            startActivity(Intent(this@MenuActivity, WeatherActivity::class.java))
        }
        logout.setOnClickListener{
            val intem : Intent = Intent(this@MenuActivity, MainActivity::class.java)
            intem.putExtra("username",username)
            startActivity(intem)
        }
        notesButton.setOnClickListener{
            val inte : Intent = Intent(this@MenuActivity, NoteActivity::class.java)
            inte.putExtra("username",username)
            startActivity(inte)
        }
        inventoryButton.setOnClickListener{
            val inten : Intent = Intent(this@MenuActivity, InventoryActivity::class.java)
            inten.putExtra("username",username)
            startActivity(inten)
        }


    }
}