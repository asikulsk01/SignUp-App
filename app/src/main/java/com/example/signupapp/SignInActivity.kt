package com.example.signupapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignInActivity : AppCompatActivity() {

    lateinit var database : DatabaseReference
    companion object{
        const val KEY1 = "com.example.signupapp.SignInActivity.uName"
        const val KEY2 = "com.example.signupapp.SignInActvity.Name"
        const val KEY3 = "com.example.signupapp.SignInActivity.Email"
        const val KEY4 = "com.example.signupapp.SignInActivity.Phone"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        val btnSignIn = findViewById<Button>(R.id.btnSignIn)
        val userName = findViewById<EditText>(R.id.etUserName)

        //Action to performed on SignIn Button click.
        btnSignIn.setOnClickListener {
            val strUserName = userName.text.toString()
            if (strUserName.isNotEmpty()){
                readData(strUserName)
            }
            else{
                Toast.makeText(this,"Please enter username",Toast.LENGTH_SHORT).show()
            }
        }
    }// onCreate method over.

    private fun readData(strUserName : String){

        //Make connection with the database.
        database = FirebaseDatabase.getInstance().getReference("Users")
        database.child(strUserName).get().addOnSuccessListener {
            //Check user exists or not.

            if (it.exists()){
                //Transfer user to WelcomeActivity using Intent.
                val userName = it.child("uname").value
                val name = it.child("name").value
                val email = it.child("email").value
                val phone = it.child("phone").value

                val intent = Intent(this, WelcomeActivity::class.java)
                intent.putExtra(KEY1, userName.toString())
                intent.putExtra(KEY2, name.toString())
                intent.putExtra(KEY3, email.toString())
                intent.putExtra(KEY4, phone.toString())
                startActivity(intent)
            }
            else{
                Toast.makeText(this, "User does not exists",Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener {
            Toast.makeText(this,"Sorry!! Database error",Toast.LENGTH_SHORT).show()
        }
    }
}