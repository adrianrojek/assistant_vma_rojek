package com.rojek.projekt

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_weather.*


class WeatherActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)

        webView()
    }
    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetJavaScriptEnabled")
    private fun webView(){
        weatherView.webViewClient = WebViewClient()

        weatherView.apply {
            loadUrl("https://weather.com/sk-SK/pocasie/dnes/l/LOXX0001:1:LO?Goto=Redirected")
            settings.javaScriptEnabled = true
            settings.safeBrowsingEnabled = true
        }
    }
}