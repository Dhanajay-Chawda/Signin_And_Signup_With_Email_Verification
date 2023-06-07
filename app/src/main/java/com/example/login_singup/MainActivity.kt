package com.example.login_singup

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var logoutDialog: AlertDialog
    private var backPressedTime: Long = 0
    private val backPressedInterval: Long = 2000 // Time interval for double press in milliseconds
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()


        val logout = findViewById<Button>(R.id.button2)
        logout.setOnClickListener {
            showLogoutDialog()
        }

    }


    private fun showLogoutDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Logout")
        builder.setMessage("Are you sure you want to logout?")

        builder.setPositiveButton("Yes") { _, _ ->
            logout()
        }

        builder.setNegativeButton("No") { dialog, _ ->
            dialog.dismiss()
        }

        logoutDialog = builder.create()
        logoutDialog.show()
    }


    private fun logout() {
        auth.signOut()

        // Create the intent to open the next activity
        val intent = Intent(this, SigninActivity::class.java)

        // Start the next activity
        startActivity(intent)
        Toast.makeText(this, "Logout successful", Toast.LENGTH_SHORT).show()
    }



    override fun onBackPressed() {
        if (backPressedTime + backPressedInterval > System.currentTimeMillis()) {
            super.onBackPressed()
            finishAffinity() // Close the app completely
        } else {
            Toast.makeText(
                this,
                "Press back again to exit",
                Toast.LENGTH_SHORT
            ).show()
        }
        backPressedTime = System.currentTimeMillis()
    }

}