package com.example.deliveryapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.deliveryapp.adapters.CakeAdapter
import com.example.deliveryapp.databinding.ActivityCategoriesBinding
import com.example.deliveryapp.models.CakeModel

class CategoriesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategoriesBinding
    private lateinit var keyName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCategoriesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        keyName = intent.extras!!["key"].toString()

        populateData()

        val recyclerView = binding.recycler
        recyclerView.layoutManager = LinearLayoutManager(this)

    }

    private fun populateData() {
        when (keyName) {
            "cakes" -> {
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

            "drinks" -> {
                val recyclerView = binding.recycler
                recyclerView.layoutManager = LinearLayoutManager(this)

                val cakes = ArrayList<CakeModel>()
                cakes.add(CakeModel("KenyaCane","150",R.drawable.cake))
                cakes.add(CakeModel("Cocacola","450",R.drawable.cake))
                cakes.add(CakeModel("Sprite","450",R.drawable.cake))
                cakes.add(CakeModel("JohnWalker","450",R.drawable.cake))
                cakes.add(CakeModel("RedLabel","450",R.drawable.cake))
                cakes.add(CakeModel("BlackForest","450",R.drawable.cake))
                cakes.add(CakeModel("BlackForest","450",R.drawable.cake))
                cakes.add(CakeModel("BlackForest","450",R.drawable.cake))

                val adapter = CakeAdapter(cakes)
                recyclerView.adapter = adapter

            }
            else -> {

                val recyclerView = binding.recycler
                recyclerView.layoutManager = LinearLayoutManager(this)

                val cakes = ArrayList<CakeModel>()
                cakes.add(CakeModel("Sushi","450",R.drawable.cake))
                cakes.add(CakeModel("Cavier","450",R.drawable.cake))
                cakes.add(CakeModel("French Fries","450",R.drawable.cake))
                cakes.add(CakeModel("BlackForest","450",R.drawable.cake))
                cakes.add(CakeModel("BlackForest","450",R.drawable.cake))
                cakes.add(CakeModel("BlackForest","450",R.drawable.cake))
                cakes.add(CakeModel("BlackForest","450",R.drawable.cake))
                cakes.add(CakeModel("BlackForest","450",R.drawable.cake))

                val adapter = CakeAdapter(cakes)
                recyclerView.adapter = adapter

            }
        }
    }
}