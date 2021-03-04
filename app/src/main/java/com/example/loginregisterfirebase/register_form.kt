package com.example.loginregisterfirebase

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_register_form.*


class register_form : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_register_form)

        mAuth = FirebaseAuth.getInstance()

        register.setOnClickListener{
            val email=email_text.text.toString().trim()
            val password=password_text.text.toString().trim()

            if(email.isEmpty()){
                email_text.error="Email Required"
                email_text.requestFocus()
                return@setOnClickListener
            }
            else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                email_text.error="Enter valid Email"
                email_text.requestFocus()
                return@setOnClickListener
            }

            if(password.isEmpty()||password.length <8){
                password_text.error="Atleast 8 character password required"
                password_text.requestFocus()
                return@setOnClickListener
            }
            loading.visibility=View.VISIBLE
            mAuth.createUserWithEmailAndPassword(email, password)
                 .addOnCompleteListener(this) { task ->
                        loading.visibility=View.INVISIBLE
                        if (task.isSuccessful) {
                            login()
                        } else {
                            task.exception?.message?.let {
                                toast(it)
                            }
                        }
                 }



}
        login_here.setOnClickListener{
            val i= Intent(this, MainActivity::class.java)
            startActivity(i)
            finish()
        }
    }
}