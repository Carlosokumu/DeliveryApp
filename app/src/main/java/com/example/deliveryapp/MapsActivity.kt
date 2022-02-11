package com.example.deliveryapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.deliveryapp.fragments.CakesFragment
import com.example.deliveryapp.utils.Extensions.requestFocusWithKeyboard

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.snackbar.Snackbar
import kotlin.math.roundToInt

class MapsActivity : AppCompatActivity(){




    private lateinit var binding : com.example.deliveryapp.databinding.ActivityMapsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = com.example.deliveryapp.databinding.ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager.beginTransaction().add(R.id.myContainer,ReminderFragment()).commit()
    }





}