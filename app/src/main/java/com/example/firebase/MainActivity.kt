package com.example.firebase

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class MainActivity : AppCompatActivity() {

    lateinit var auth: FirebaseAuth
    lateinit var buttonlogout: Button
    lateinit var textView: TextView
    lateinit var user: FirebaseUser


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()
        buttonlogout = findViewById<Button>(R.id.button3)
        textView = findViewById<TextView>(R.id.maintextview)
        user = auth.currentUser!!
        if (user == null) {
            val intent = Intent(this, login::class.java)
            startActivity(intent)
            finish()
        } else {
            textView.setText(user.email)

        }
        buttonlogout.setOnClickListener { logout() }


    }

    fun logout() {
        FirebaseAuth.getInstance().signOut()
        val intent = Intent(this, login::class.java)
        startActivity(intent)
        finish()
    }
}