package com.example.deliveryapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.recyclerview.widget.RecyclerView
import com.example.deliveryapp.R

class CakesAdapter: RecyclerView.Adapter<BaseViewHolder>() {

    private lateinit var cakes:  List<Cake>


    fun setCakes(cakes: List<Cake>){
          this.cakes = cakes
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
          val view = LayoutInflater.from(parent.context).inflate(R.layout.row_items,
        parent,
        false)
          return BaseViewHolder(view)
    }



    override fun getItemCount(): Int {
        return cakes.size
    }


    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {

    }
}

class Cake(name: String) {

}
