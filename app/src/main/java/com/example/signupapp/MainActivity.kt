package com.example.signupapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val signupButton= findViewById<Button>(R.id.btnSignup)
        val etUserName= findViewById<EditText>(R.id.etUserName)
        val etName= findViewById<EditText>(R.id.etName)
        val etEmail= findViewById<EditText>(R.id.etEmail)
        val etPhone= findViewById<EditText>(R.id.etPhone)
        val etPassword= findViewById<EditText>(R.id.etPassword)


        signupButton.setOnClickListener {

            val uname= etUserName.text.toString()
            val name= etName.text.toString()
            val email= etEmail.text.toString()
            val phone= etPhone.text.toString()
            val password= etPassword.text.toString()

            //Object creation of User Kotlin class
            val user = User(uname, name, email, phone, password)

            database = FirebaseDatabase.getInstance().getReference("Users")

            //Adding values into the database and display Toast message.
            database.child(uname).setValue(user).addOnSuccessListener {

                //After submission all field will be clear.
                etUserName.text?.clear()
                etName.text?.clear()
                etEmail.text?.clear()
                etPhone.text?.clear()
                etPassword.text?.clear()

                Toast.makeText(this,"User has been registered", Toast.LENGTH_SHORT).show()
            }.addOnCanceledListener {
                Toast.makeText(this,"User not registered",Toast.LENGTH_SHORT).show()
            }

        }
    }
}