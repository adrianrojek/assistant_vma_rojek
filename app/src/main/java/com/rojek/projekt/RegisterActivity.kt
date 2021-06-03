package com.rojek.projekt

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.rojek.projekt.db.AppDatabase
import com.rojek.projekt.entities.User
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_register.view.*

class RegisterActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    val CHANNEL_ID = "channel_id"
    val notificationId = 1
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        createNotificationChannel()

        var insertUser = findViewById(R.id.insertUser) as Button

        insertUser.setOnClickListener{
            sendNotification()
            insertUserToDb()

        }


    }
    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val name = "Upozornenie"
            val notificationDescription= "Notifikácia o úspešnej registrácií"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID,name,importance).apply {
                description = notificationDescription
            }
            val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
    private fun sendNotification(){
        val meno = username.text.toString()
        val heslo = password.text.toString()
        val builder = NotificationCompat.Builder(this,CHANNEL_ID)
                .setSmallIcon(R.drawable.cloud)
            .setContentTitle("Registrácia prebehla úspešne")
            .setContentText("Meno: "+meno+", Heslo: "+heslo)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        with(NotificationManagerCompat.from(this)){
            notify(notificationId,builder.build())
        }
    }

    fun insertUserToDb(){
        val usernameET = findViewById(R.id.username) as EditText
        val passwordET = findViewById(R.id.password) as EditText
        val passwordCheckET = findViewById(R.id.passwordCheck) as EditText

        val username = usernameET.text.toString()
        val password = passwordET.text.toString()
        val passwordCheck = passwordCheckET.text.toString()

        val userDao = AppDatabase.invoke(this)?.userDao()
        val newUser = userDao?.getUserByUsername(username)
        if (newUser == null) {
            if(password == passwordCheck) {
                val newUser = User(username, password)
                userDao?.insertUser(newUser)
                Toast.makeText(this, "Registrácia prebehla úspešne!", Toast.LENGTH_LONG).show()
                startActivity(Intent(this@RegisterActivity, MainActivity::class.java))
            }else{
                Toast.makeText(this,"Heslá sa musia zhodovať!", Toast.LENGTH_LONG).show()
            }
        }else{
            Toast.makeText(this,"Používateľ so zadaným používateľským menom už existuje!", Toast.LENGTH_LONG).show()
        }


    }
}