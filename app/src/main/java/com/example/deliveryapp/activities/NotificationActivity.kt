package com.example.deliveryapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.deliveryapp.adapters.NotificationAdapter
import com.example.deliveryapp.databinding.ActivityNotificationBinding
import com.example.deliveryapp.models.NotificationData
import com.example.deliveryapp.utils.ObjectBox

class NotificationActivity : AppCompatActivity() {



    private lateinit var binding: ActivityNotificationBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotificationBinding.inflate(layoutInflater)
        setContentView(binding.root)


        if (!ObjectBox.store.boxFor(NotificationData::class.java).isEmpty){
            binding.recyclerMessages.adapter = NotificationAdapter(ObjectBox.store.boxFor(
                NotificationData::class.java).all as ArrayList<NotificationData>)
        }
        else {
            Toast.makeText(this,"Box is Empty",Toast.LENGTH_SHORT).show()
        }

        //binding.recyclerMessages.layoutManager = LinearLayoutManager(this,UCharacter.VerticalOrientation)
    }
}