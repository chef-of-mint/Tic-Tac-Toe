package com.example.loginregisterfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAuth= FirebaseAuth.getInstance()

        loginM.setOnClickListener{
            val email=email_textM.text.toString().trim()
            val password=password_textM.text.toString().trim()

            if(email.isEmpty()){
                email_textM.error="Email Required"
                email_textM.requestFocus()
                return@setOnClickListener
            }
            else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                email_textM.error="Enter valid Email"
                email_textM.requestFocus()
                return@setOnClickListener
            }

            if(password.isEmpty()||password.length <8){
                password_textM.error="Atleast 8 character password required"
                password_textM.requestFocus()
                return@setOnClickListener
            }

            loadingM.visibility= View.VISIBLE
            mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    loadingM.visibility= View.INVISIBLE
                    if (task.isSuccessful) {
                        login()
                    } else {
                        task.exception?.message?.let {
                            toast(it)
                        }
                    }
                }
        }

    register_yourselfM.setOnClickListener{
        val j=Intent(this,register_form::class.java)
        startActivity(j)
        finish()
    }
    }

    override fun onStart() {
        super.onStart()
        mAuth.currentUser?.let {
            login()
        }
    }
}