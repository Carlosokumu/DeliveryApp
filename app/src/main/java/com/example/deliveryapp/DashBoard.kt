package com.example.deliveryapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.commit
import com.example.deliveryapp.databinding.ActivityDashBoardBinding
import com.example.deliveryapp.databinding.ActivitySignInBinding
import com.example.deliveryapp.databinding.ActivitySignUpBinding
import com.example.deliveryapp.fragments.CakesFragment

class DashBoard : AppCompatActivity(), View.OnClickListener {


    private lateinit var binding:  ActivityDashBoardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashBoardBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.cakes.setOnClickListener(this)
        binding.dinner.setOnClickListener(this)
        binding.drinks.setOnClickListener(this)
        binding.adminSection.setOnClickListener(this)
        binding.deliverer.setOnClickListener(this)


    }

    override fun onClick(view: View) {
        when(view){
            binding.cakes ->{
                 val intent = Intent(this,ItemsManager::class.java).putExtra("WHAT","cake")
                startActivity(intent)
            }
            binding.dinner ->{
                val intent = Intent(this,ItemsManager::class.java).putExtra("WHAT","dinner")
                startActivity(intent)
            }
            binding.drinks ->{
                val intent = Intent(this,ItemsManager::class.java).putExtra("WHAT","drinks")
                startActivity(intent)
            }
            binding.adminSection ->{
                val intent = Intent(this,AdminSection::class.java)
                startActivity(intent)
            }
            binding.deliverer ->{
                val intent = Intent(this,RegisterDeliverer::class.java)
                startActivity(intent)
            }
        }
    }
}