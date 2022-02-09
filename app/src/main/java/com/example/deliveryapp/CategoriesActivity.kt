package com.example.deliveryapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.deliveryapp.adapters.CakeAdapter
import com.example.deliveryapp.databinding.ActivityCategoriesBinding
import com.example.deliveryapp.models.CakeModel

class CategoriesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategoriesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCategoriesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerView = binding.recycler
        recyclerView.layoutManager = LinearLayoutManager(this)

        val cakes = ArrayList<CakeModel>()
        cakes.add(CakeModel("BlackForest","450",R.drawable.cake))
        cakes.add(CakeModel("WhiteForest","450",R.drawable.cake))
        cakes.add(CakeModel("BlackForest","450",R.drawable.cake))
        cakes.add(CakeModel("BlackForest","450",R.drawable.cake))
        cakes.add(CakeModel("BlackForest","450",R.drawable.cake))
        cakes.add(CakeModel("BlackForest","450",R.drawable.cake))
        cakes.add(CakeModel("BlackForest","450",R.drawable.cake))
        cakes.add(CakeModel("BlackForest","450",R.drawable.cake))

        val adapter = CakeAdapter(cakes)
        recyclerView.adapter = adapter

    }
}