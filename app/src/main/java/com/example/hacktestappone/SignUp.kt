package com.example.hacktestappone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class SignUp : AppCompatActivity() {
    lateinit var auth: FirebaseAuth;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up);
        auth=FirebaseAuth.getInstance()
        val userEmail = findViewById<EditText>(R.id.EmailAddress);
        val firstPassword = findViewById<EditText>(R.id.Password1);
        val secondPassword = findViewById<EditText>(R.id.Password2);
        val signUpBtn = findViewById<Button>(R.id.SignUp);

        signUpBtn.setOnClickListener {
            handleSignUp(userEmail,firstPassword,secondPassword);
        }
    }
    fun handleSignUp(emailD:EditText,password1D:EditText,password2D:EditText){
        var email = emailD.text.toString();
        var password1 = password1D.text.toString();
        var password2 =password2D.text.toString();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Enter Email",Toast.LENGTH_SHORT).show();
            return;
        }

        if(password1==password2 && TextUtils.isEmpty(password1)||TextUtils.isEmpty(password2)){
            Toast.makeText(this,"Enter Password correctly",Toast.LENGTH_SHORT).show();
            return;
        }



            auth.createUserWithEmailAndPassword(email, password1)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information

                        val user = auth.currentUser

                       Toast.makeText(this,"",Toast.LENGTH_SHORT).show()
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

