package com.example.deliveryapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.commit
import com.example.deliveryapp.databinding.ActivityDashBoardBinding
import com.example.deliveryapp.databinding.ActivitySignInBinding
import com.example.deliveryapp.databinding.ActivitySignUpBinding
import com.example.deliveryapp.fragments.CakesFragment

class DashBoard : AppCompatActivity(), View.OnClickListener {


    private lateinit var binding:  ActivityDashBoardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash_board)
        binding = ActivityDashBoardBinding.inflate(layoutInflater)


        binding.cakes.setOnClickListener(this)
        binding.dinner.setOnClickListener(this)
        binding.drinks.setOnClickListener(this)



    }

    override fun onClick(view: View) {
        when(view){
            binding.cakes ->{
                
            }
            binding.dinner ->{

            }
            binding.drinks ->{

            }
        }
    }
}