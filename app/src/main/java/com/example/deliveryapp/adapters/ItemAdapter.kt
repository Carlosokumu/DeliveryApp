package com.example.deliveryapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.deliveryapp.R
import com.example.deliveryapp.fragments.PaymentFragment
import com.example.deliveryapp.models.ItemsModel

class ItemAdapter(val itemList: ArrayList<ItemsModel>) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bindItems(items: ItemsModel){
            val tvItemName = itemView.findViewById<TextView>(R.id.name)
            val tvItemPrice = itemView.findViewById<TextView>(R.id.price)
            val ivItemImage = itemView.findViewById<ImageView>(R.id.image)

            tvItemName.text = items.name
            tvItemPrice.text = items.price
            ivItemImage.setImageResource(items.image)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_items,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bindItems(itemList[position])

        holder.itemView.setOnClickListener {
            PaymentFragment.newInstance().show((holder.itemView.context as AppCompatActivity).supportFragmentManager,"Payment")
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }


}