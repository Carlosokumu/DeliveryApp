package com.example.deliveryapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.deliveryapp.adapters.ItemAdapter
import com.example.deliveryapp.databinding.ActivityCategoriesBinding
import com.example.deliveryapp.models.ItemsModel

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

                val cakes = ArrayList<ItemsModel>()
                cakes.add(ItemsModel("BlackForest","450",R.drawable.cake))
                cakes.add(ItemsModel("WhiteForest","450",R.drawable.cake))
                cakes.add(ItemsModel("BlackForest","450",R.drawable.cake))
                cakes.add(ItemsModel("BlackForest","450",R.drawable.cake))
                cakes.add(ItemsModel("BlackForest","450",R.drawable.cake))
                cakes.add(ItemsModel("BlackForest","450",R.drawable.cake))
                cakes.add(ItemsModel("BlackForest","450",R.drawable.cake))
                cakes.add(ItemsModel("BlackForest","450",R.drawable.cake))

                val adapter = ItemAdapter(cakes)
                recyclerView.adapter = adapter
            }

            "drinks" -> {
                val recyclerView = binding.recycler
                recyclerView.layoutManager = LinearLayoutManager(this)

                val cakes = ArrayList<ItemsModel>()
                cakes.add(ItemsModel("KenyaCane","150",R.drawable.cake))
                cakes.add(ItemsModel("Cocacola","450",R.drawable.cake))
                cakes.add(ItemsModel("Sprite","450",R.drawable.cake))
                cakes.add(ItemsModel("JohnWalker","450",R.drawable.cake))
                cakes.add(ItemsModel("RedLabel","450",R.drawable.cake))
                cakes.add(ItemsModel("BlackForest","450",R.drawable.cake))
                cakes.add(ItemsModel("BlackForest","450",R.drawable.cake))
                cakes.add(ItemsModel("BlackForest","450",R.drawable.cake))

                val adapter = ItemAdapter(cakes)
                recyclerView.adapter = adapter

            }
            else -> {

                val recyclerView = binding.recycler
                recyclerView.layoutManager = LinearLayoutManager(this)

                val cakes = ArrayList<ItemsModel>()
                cakes.add(ItemsModel("Sushi","450",R.drawable.cake))
                cakes.add(ItemsModel("Cavier","450",R.drawable.cake))
                cakes.add(ItemsModel("French Fries","450",R.drawable.cake))
                cakes.add(ItemsModel("BlackForest","450",R.drawable.cake))
                cakes.add(ItemsModel("BlackForest","450",R.drawable.cake))
                cakes.add(ItemsModel("BlackForest","450",R.drawable.cake))
                cakes.add(ItemsModel("BlackForest","450",R.drawable.cake))
                cakes.add(ItemsModel("BlackForest","450",R.drawable.cake))

                val adapter = ItemAdapter(cakes)
                recyclerView.adapter = adapter

            }
        }
    }
}