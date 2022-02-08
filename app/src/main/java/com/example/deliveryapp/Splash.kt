package com.example.deliveryapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.google.firebase.auth.FirebaseAuth

class Splash : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        mAuth = FirebaseAuth.getInstance()

        Handler().postDelayed({ //This method will be executed once the timer is over
            // Start your app main activity
            if (mAuth.currentUser != null){
                val i = Intent(this@Splash,DashBoard::class.java)
                startActivity(i)
            }
            else {
                val i = Intent(this@Splash, WalkThrough::class.java)
                startActivity(i)
                // close this activity
                finish()
            }

        }, 5000)
    }
}