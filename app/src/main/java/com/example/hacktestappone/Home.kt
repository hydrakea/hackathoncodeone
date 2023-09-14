package com.example.hacktestappone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import kotlin.math.sign

class Home : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        auth=FirebaseAuth.getInstance()
        val setText = findViewById<TextView>(R.id.userDetails);
        val logout = findViewById<Button>(R.id.logout);
        val user = auth.currentUser
        if (user != null) {
            setText.setText(user?.email)
        };
        logout.setOnClickListener {
            FirebaseAuth.getInstance().signOut();
            val intent = Intent(this,SignIn::class.java);
            startActivity(intent);
        }
    }
}