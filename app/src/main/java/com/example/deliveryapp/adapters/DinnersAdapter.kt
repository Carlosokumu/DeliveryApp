package com.example.deliveryapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.deliveryapp.R
import com.example.deliveryapp.fragments.PaymentFragment
import com.example.deliveryapp.fragments.PreviewFragment
import com.example.deliveryapp.models.CakeModel
import com.example.deliveryapp.utils.Extensions.asOderInfo
import com.google.firebase.auth.FirebaseAuth

class DinnersAdapter(val cakes: List<CakeModel>,val context: Context): RecyclerView.Adapter<BaseViewHolder>() {





    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.itemlayout,
            parent,
            false)
        return BaseViewHolder(view)
    }



    override fun getItemCount(): Int {
        return cakes.size
    }


    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {

        holder.itemView.setOnClickListener {
            (context as AppCompatActivity). supportFragmentManager.beginTransaction().replace(R.id.fragment_container_view,
                PreviewFragment.newInstance(
                    name = cakes[position].name,
                    price = cakes[position].price,
                    image = cakes[position].image,
                    description = holder.itemView.context.getString(R.string.sample)
                )
            ).addToBackStack("").commit()
        }

        holder.itemView.findViewById<TextView>(R.id.price).text = cakes[position].price
        holder.itemView.findViewById<TextView>(R.id.txtFoodName).text = cakes[position].name
        holder.itemView.findViewById<ImageView>(R.id.roundedImageView).setImageResource(cakes[position].image)
//           holder.itemView.setOnClickListener {
//               PaymentFragment.newInstance(cakes[position].asOderInfo(FirebaseAuth.getInstance().currentUser?.email!!),context =holder.itemView.context).show((holder.itemView.context as AppCompatActivity).supportFragmentManager,"Payment")
//           }
//        val tvCakeName = holder.itemView.findViewById<TextView>(R.id.name)
//        val tvCakePrice = holder.itemView.findViewById<TextView>(R.id.price)
//        val ivCakeImage = holder.itemView.findViewById<de.hdodenhof.circleimageview.CircleImageView>(R.id.image)
//
//        tvCakeName.text = cakes[position].name
//        tvCakePrice.text = cakes[position].price
//        ivCakeImage.setImageResource(cakes[position].image)
    }
}

