package com.example.deliveryapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.deliveryapp.R
import com.example.deliveryapp.fragments.PaymentFragment
import com.example.deliveryapp.models.CakeModel
import com.example.deliveryapp.utils.Extensions.asOderInfo
import com.google.firebase.auth.FirebaseAuth

class DrinksAdapter(val cakes: List<CakeModel>): RecyclerView.Adapter<BaseViewHolder>() {



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
            PaymentFragment.newInstance(cakes[position].asOderInfo(FirebaseAuth.getInstance().currentUser?.email!!),context = holder.itemView.context).show((holder.itemView.context as AppCompatActivity).supportFragmentManager,"Payment")
        }
        val tvCakeName = holder.itemView.findViewById<TextView>(R.id.name)
        val tvCakePrice = holder.itemView.findViewById<TextView>(R.id.price)
        val ivCakeImage = holder.itemView.findViewById<de.hdodenhof.circleimageview.CircleImageView>(R.id.image)

        tvCakeName.text = cakes[position].name
        tvCakePrice.text = cakes[position].price
        ivCakeImage.setImageResource(cakes[position].image)

    }
}

