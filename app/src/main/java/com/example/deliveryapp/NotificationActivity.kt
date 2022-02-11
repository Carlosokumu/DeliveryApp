package com.example.deliveryapp

import android.icu.lang.UCharacter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.deliveryapp.adapters.NotificationAdapter
import com.example.deliveryapp.databinding.ActivityNotificationBinding
import com.example.deliveryapp.databinding.ActivitySignUpBinding
import com.example.deliveryapp.utils.ObjectBox

class NotificationActivity : AppCompatActivity() {



    private lateinit var binding: ActivityNotificationBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotificationBinding.inflate(layoutInflater)
        setContentView(binding.root)


        if (!ObjectBox.store.boxFor(NotificationData::class.java).isEmpty){
            binding.recyclerMessages.adapter = NotificationAdapter(ObjectBox.store.boxFor(NotificationData::class.java).all as ArrayList<NotificationData>)
        }
        else {
            Toast.makeText(this,"Box is Empty",Toast.LENGTH_SHORT).show()
        }

        //binding.recyclerMessages.layoutManager = LinearLayoutManager(this,UCharacter.VerticalOrientation)
    }
}