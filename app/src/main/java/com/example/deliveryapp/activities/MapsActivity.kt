package com.example.deliveryapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.deliveryapp.R
import com.example.deliveryapp.ReminderFragment

class MapsActivity : AppCompatActivity(){




    private lateinit var binding : com.example.deliveryapp.databinding.ActivityMapsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = com.example.deliveryapp.databinding.ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager.beginTransaction().add(R.id.myContainer, ReminderFragment()).commit()
    }





}