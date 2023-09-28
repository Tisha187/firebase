package com.example.firebase

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import java.lang.Double

class login : AppCompatActivity() {

    lateinit var edittextemail: TextInputEditText
    lateinit var edittextpassword: TextInputEditText
    lateinit var buttonlogin: Button
    lateinit var mAuth: FirebaseAuth
    lateinit var progreesbar: ProgressBar
    lateinit var TextView:TextView
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = mAuth.currentUser
        if (currentUser != null) {
            val intent=Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        var loginbutton=findViewById<TextView>(R.id.loginNow)
        mAuth=FirebaseAuth.getInstance()
        edittextemail=findViewById<TextInputEditText>(R.id.Email)
        edittextpassword=findViewById<TextInputEditText>(R.id.Password)
        buttonlogin=findViewById<Button>(R.id.button1)
        progreesbar=findViewById<ProgressBar>(R.id.progress_bar)
        TextView=findViewById<TextView>(R.id.loginNow)
        TextView.setOnClickListener { newActiviy() }
        buttonlogin.setOnClickListener{ clickonbtn()}


    }
    fun newActiviy(){
        val intent = Intent(this, registration::class.java)
        startActivity(intent)
        finish()
    }
    fun clickonbtn(){
        var Email= (edittextemail.text.toString())
        var password= (edittextpassword.text.toString())
        progreesbar.visibility= View.VISIBLE

        if(TextUtils.isEmpty(Email.toString())){
            Toast.makeText( this@login,"please Enter email", Toast.LENGTH_SHORT).show()
            return
        }

        if(TextUtils.isEmpty(password.toString())){
            Toast.makeText( this@login,"please Enter your password", Toast.LENGTH_SHORT).show()
            return
        }

        mAuth.signInWithEmailAndPassword(Email.toString(), password.toString())
            .addOnCompleteListener(this) { task ->
                progreesbar.visibility=View.GONE
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Toast.makeText(
                        baseContext,
                        "you have successfully logged in",
                        Toast.LENGTH_SHORT,
                    ).show()

                    val intent=Intent(this,MainActivity::class.java)
                    startActivity(intent)
                    finish()

                } else {
                    // If sign in fails, display a message to the user.

                    Toast.makeText(
                        baseContext,
                        "Authentication failed.",
                        Toast.LENGTH_SHORT,
                    ).show()

                }
            }

    }
}