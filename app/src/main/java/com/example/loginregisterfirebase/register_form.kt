package com.example.loginregisterfirebase

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_register_form.*


class register_form : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth

    val database = FirebaseDatabase.getInstance()
    val myRef = database.getReference("user").push()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_register_form)

        mAuth = FirebaseAuth.getInstance()


        register.setOnClickListener{
            val email=email_text.text.toString().trim()
            val password=password_text.text.toString().trim()
            val name=name_text.text.toString().trim()
            if(name.isEmpty()){
                name_text.error="Name Required"
                name_text.requestFocus()
                return@setOnClickListener
            }
            else if(email.isEmpty()){
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

                            val currentUser = FirebaseAuth.getInstance().currentUser



                            currentUser?.let{ user->

                                myRef.child("id").setValue(user.uid)
                                myRef.child("name").setValue(name)
                                myRef.child("email").setValue(user.email)
                                myRef.child("profileUrl").setValue("https://picsum.photos/200")
                                myRef.child("Rating").setValue("1700")
                            }
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