package com.example.deliveryapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.deliveryapp.fragments.CakesFragment
import com.example.deliveryapp.fragments.DinnerFragment
import com.example.deliveryapp.fragments.Drinks

class ItemsManager : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_items_manager)



        when(intent.getStringExtra("WHAT")){
            "cake" ->{
                 supportFragmentManager.beginTransaction().add(R.id.fragment_container_view,CakesFragment()).commit()
            }
            "drinks"->{
                supportFragmentManager.beginTransaction().add(R.id.fragment_container_view,Drinks()).commit()
            }
            "dinner" ->{
                supportFragmentManager.beginTransaction().add(R.id.fragment_container_view,DinnerFragment()).commit()
            }

        }
    }
}