package com.example.deliveryapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.deliveryapp.databinding.ActivityDashBoardBinding

class DashBoard : AppCompatActivity() {

    private lateinit var binding: ActivityDashBoardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDashBoardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.cakes.setOnClickListener {
            val intent = Intent(this,CategoriesActivity::class.java)
            startActivity(intent)
        }
    }
}