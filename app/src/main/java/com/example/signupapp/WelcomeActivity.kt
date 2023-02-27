package com.example.signupapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView

class WelcomeActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        //Catch the values that are passed through intent.
        val uname = intent.getStringExtra(SignInActivity.KEY1)
        val name = intent.getStringExtra(SignInActivity.KEY2)
        val email = intent.getStringExtra(SignInActivity.KEY3)
        val phone = intent.getStringExtra(SignInActivity.KEY4)

        val welcomeMsg = findViewById<TextView>(R.id.tvWelcome)
        val nameText = findViewById<TextView>(R.id.tvWlcmName)
        val emailText = findViewById<TextView>(R.id.tvWlcmEmail)
        val phoneText = findViewById<TextView>(R.id.tvWlcmPhone)

        welcomeMsg.text = "Welcome $uname"
        nameText.text = "$name"
        emailText.text = "$email"
        phoneText.text = "$phone"

    }
}