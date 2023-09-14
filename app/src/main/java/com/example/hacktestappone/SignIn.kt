package com.example.hacktestappone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class SignIn : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if (currentUser != null) {
            val intent = Intent(this,Home::class.java);
            startActivity(intent);
            finish()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        auth=FirebaseAuth.getInstance()
        val userEmailAddress = findViewById<EditText>(R.id.EmailAddress);
        val userPassword = findViewById<EditText>(R.id.passwordInput)
        val signInBtn = findViewById<Button>(R.id.signInbutton)
        val navigateToSignUp = findViewById<Button>(R.id.signUpNavigateBtn);

        signInBtn.setOnClickListener {
            handleSignIn(userEmailAddress,userPassword);
        }

navigateToSignUp.setOnClickListener {
    val intent = Intent(this,SignUp::class.java);
    startActivity(intent);
}




    }
    fun handleSignIn(emailD:EditText,passwordD:EditText){
        var email = emailD.text.toString();
        var password1 = passwordD.text.toString();


        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Enter Email",Toast.LENGTH_SHORT).show();
            return;
        }

       if(TextUtils.isEmpty(password1)){
           Toast.makeText(this,"Enter Password",Toast.LENGTH_SHORT).show();
           return;
       }
        auth.signInWithEmailAndPassword(email, password1)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information

                    val user = auth.currentUser
val intent = Intent(this,Home::class.java);
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