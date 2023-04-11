package com.example.login_singup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()


         val logout = findViewById<Button>(R.id.button2)
        logout.setOnClickListener {

            auth.signOut()

            // Create the intent to open the next activity
            val intent = Intent(this,SigninActivity::class.java)

            // Start the next activity
            startActivity(intent)
            Toast.makeText(this,"Logout  successful", Toast.LENGTH_SHORT).show()



        }


    }
}