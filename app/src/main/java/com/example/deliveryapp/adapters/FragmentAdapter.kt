package com.example.deliveryapp.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.deliveryapp.fragments.DeliverersList
import com.example.deliveryapp.fragments.ClientOrders

class FragmentAdapter(fragmentManager: FragmentManager): FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){
    private val fragmentss = listOf(ClientOrders(), DeliverersList())
    private val titles = listOf("Client Orders", "Deliverers")
    override fun getCount(): Int = fragmentss.size

    override fun getItem(position: Int): Fragment = fragmentss[position]
    override fun getPageTitle(position: Int): CharSequence? = titles[position]
}