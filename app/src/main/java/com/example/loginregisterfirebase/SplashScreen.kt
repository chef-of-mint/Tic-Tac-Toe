package com.example.loginregisterfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_splash_screen.*

//@Suppress("DEPRECATION")
class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        supportActionBar?.hide()

        val imgAnim = AnimationUtils.loadAnimation(this,R.anim.img_animation)
        val txtAnim = AnimationUtils.loadAnimation(this,R.anim.txt_animation)

        topImage.startAnimation(imgAnim)
        bottomText.startAnimation(txtAnim)

        Handler().postDelayed({
            val i= Intent(this, MainActivity::class.java)
            startActivity(i)
            finish()
        }, 2200)
    }
}