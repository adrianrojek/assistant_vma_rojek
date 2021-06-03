package com.rojek.projekt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import com.rojek.projekt.db.AppDatabase
import com.rojek.projekt.entities.User

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var loginButton = findViewById(R.id.loginButton) as Button
        var registerButton = findViewById(R.id.registerButton) as Button



        loginButton.setOnClickListener {
            login()
        }
        registerButton.setOnClickListener {
            startActivity(Intent(this@MainActivity, RegisterActivity::class.java))
        }

    }

    fun login(){
        val usernameET = findViewById(R.id.usernameLogin) as EditText
        val passwordET = findViewById(R.id.passwordLogin) as EditText

        val username = usernameET.text.toString()
        val password = passwordET.text.toString()


        val userDao = AppDatabase.invoke(this)?.userDao()
        val newUser = userDao?.getUserByUsername(username)
        if (newUser == null){
            Toast.makeText(this,"Používateľ so zadaným používateľským menom neexistuje!" , Toast.LENGTH_LONG).show()
        }else{
            if (password == newUser.password){
                Toast.makeText(this,"Prihlásenie prebehlo úspešne!", Toast.LENGTH_LONG).show()
                val intent : Intent = Intent(this, MenuActivity::class.java)
                intent.putExtra("username",username)
                startActivity(intent)
            }else{
                Toast.makeText(this,"Nesprávne heslo!", Toast.LENGTH_LONG).show()
            }
        }





    }
}