package com.example.deliveryapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.deliveryapp.adapters.FragmentAdapter
import com.example.deliveryapp.databinding.ActivityAdminSectionBinding
import com.example.deliveryapp.databinding.ActivityDashBoardBinding

class AdminSection : AppCompatActivity() {


    private lateinit var binding: ActivityAdminSectionBinding
    private val mainPagerAdapter: FragmentAdapter by lazy {
        FragmentAdapter(supportFragmentManager)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminSectionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.viewpager.adapter = mainPagerAdapter
        binding.tabs.setupWithViewPager( binding.viewpager)

    }
}