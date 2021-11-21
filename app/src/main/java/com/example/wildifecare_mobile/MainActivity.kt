package com.example.wildifecare_mobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val goToWorkSpaceButton: Button = findViewById (R.id.goToWorkSpaceButton)

        goToWorkSpaceButton.setOnClickListener() {
            val i = Intent(this, LoginActivity::class.java)
            val workspaceUrl : EditText = findViewById(R.id.workspaceUrl);
            var getUrl : String = workspaceUrl.getText().toString();
//Create the bundle
            var bundle : Bundle = Bundle();

//Add your data to bundle
            bundle.putString("url", getUrl);

//Add the bundle to the intent
            i.putExtras(bundle);

//Fire that second activity
            startActivity(i);
        }
    }
}