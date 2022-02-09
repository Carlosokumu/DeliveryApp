package com.example.deliveryapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.deliveryapp.OrderInfo
import com.example.deliveryapp.R

class OdersAdapter(val order: List<OrderInfo>): RecyclerView.Adapter<BaseViewHolder>() {




    override  fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.row_oder,
            parent,
            false)
        return BaseViewHolder(view)
    }



    override fun getItemCount(): Int {
        return order.size
    }


    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
          holder.itemView.findViewById<TextView>(R.id.price).setText(order[position].price)
          holder.itemView.findViewById<TextView>(R.id.namef).setText(order[position].name)
           holder.itemView.findViewById<TextView>(R.id.oderer).setText(order[position].orderedBy)

    }
}
