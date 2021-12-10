package com.example.wildifecare_mobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        var bundle : Bundle? = intent.extras
        var url : String? = bundle?.getString("url")
        val textView: TextView = findViewById(R.id.textView3) as TextView
        textView.text = url

        val loginButton: Button = findViewById (R.id.loginButton)

        loginButton.setOnClickListener() {
            val i = Intent(this, MapsActivity::class.java)
            startActivity(i);
        }
    }
}