package com.example.deliveryapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.deliveryapp.interfaces.OnItemListener
import com.example.deliveryapp.R
import com.example.deliveryapp.models.CakeModel
import com.example.deliveryapp.utils.Extensions.asOderInfo
import com.google.firebase.auth.FirebaseAuth

class CakeAdapter(val cakesList: ArrayList<CakeModel>) : RecyclerView.Adapter<CakeAdapter.ViewHolder>() {

    private  lateinit var listener: OnItemListener
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bindItems(cake: CakeModel){
            val tvCakeName = itemView.findViewById<TextView>(R.id.tvFoodName)
            val tvCakePrice = itemView.findViewById<TextView>(R.id.tvPrice)
            val ivCakeImage = itemView.findViewById<ImageView>(R.id.ivFood)

            tvCakeName.text = cake.name
            tvCakePrice.text = cake.price
            ivCakeImage.setImageResource(cake.image)

        }

    }

    fun setListener(listener: OnItemListener){
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_items,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bindItems(cakesList[position])
        holder.itemView.setOnClickListener {
             listener.clientOrder(cakesList[position].asOderInfo(FirebaseAuth.getInstance().currentUser?.displayName!!))
        }
    }

    override fun getItemCount(): Int {
        return cakesList.size
    }


}