package com.example.deliveryapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.deliveryapp.R
import com.example.deliveryapp.fragments.PaymentFragment

class DrinksAdapter(val cakes: List<Cake>): RecyclerView.Adapter<BaseViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.row_items,
            parent,
            false)
        return BaseViewHolder(view)
    }



    override fun getItemCount(): Int {
        return cakes.size
    }


    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            PaymentFragment.newInstance().show((holder.itemView.context as AppCompatActivity).supportFragmentManager,"Payment")
        }

    }
}

