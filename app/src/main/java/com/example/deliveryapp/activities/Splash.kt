package com.example.deliveryapp.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.deliveryapp.R
import com.example.deliveryapp.utils.Settings
import com.google.firebase.auth.FirebaseAuth
import com.sdsmdg.tastytoast.TastyToast


class Splash : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        mAuth = FirebaseAuth.getInstance()



        Handler().postDelayed({ //This method will be executed once the timer is over
            // Start your app main activity
//            if (mAuth.currentUser != null) {
//                if (Settings.isAdmin()!!) {
//                    val i = Intent(this@Splash, AdminSection::class.java)
//                    TastyToast.makeText(
//                        this,
//                        "Logged as Admin",
//                        TastyToast.LENGTH_SHORT,
//                        TastyToast.INFO
//                    ).show()
//                    startActivity(i)
//                    finish()
//                } else if (Settings.isCustomer()!!) {
//                    val i = Intent(this@Splash, DashBoard::class.java)
//                    TastyToast.makeText(
//                        this,
//                        "Logged as Customer",
//                        TastyToast.LENGTH_SHORT,
//                        TastyToast.INFO
//                    ).show()
//                    Toast.makeText(this, "r", Toast.LENGTH_SHORT).show()
//                    startActivity(i)
//                    finish()
//                } else if (Settings.isDeliverer()!!) {
//                    val i = Intent(this@Splash, DelivererActivity::class.java)
//                    TastyToast.makeText(
//                        this,
//                        "Logged as a Deliverer",
//                        TastyToast.LENGTH_SHORT,
//                        TastyToast.INFO
//                    ).show()
//                    startActivity(i)
//                    finish()
//                }
//            } else {
//                val i = Intent(this@Splash, WalkThrough::class.java)
//                startActivity(i)
//                finish()
//            }


            //s.isCustomer()!!){
//                    val i = Intent(this@Splash,DashBoard::class.java)
//                    startActivity(i)
//                    finish()
//                }else if(Settings.isAdmin()!!){
//                    val i = Intent(this@Splash,AdminSection::class.java)
//                    startActivity(i)
//                    finish()
//                }
//                else if(Settings.isDeliverer()!!){
//                    val i = Intent(this@Splash,DashBoard::class.java)
//                    startActivity(i)
//                    finish()
//                }

            startActivity(Intent(this@Splash,DashBoard::class.java))
        }, 5000)
    }
}