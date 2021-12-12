package com.example.wildifecare_mobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.wildifecare_mobile.model.NationalPark

class LoginActivity : AppCompatActivity() {
    var nationalPark : NationalPark? = null;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        var bundle : Bundle? = intent.extras
        nationalPark = bundle?.getSerializable("nationalPark") as NationalPark?
        val textView: TextView = findViewById(R.id.textView3) as TextView
        if (nationalPark != null) {
            textView.text = nationalPark!!.name
        }

        val loginButton: Button = findViewById (R.id.loginButton)

        loginButton.setOnClickListener() {
            val i = Intent(this, MapsActivity::class.java)
            var bundle : Bundle = Bundle();
            bundle.putSerializable("nationalPark", nationalPark)
            i.putExtras(bundle)
            startActivity(i);
        }
    }
}