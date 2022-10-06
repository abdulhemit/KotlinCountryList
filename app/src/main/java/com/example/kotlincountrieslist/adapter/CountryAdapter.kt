package com.example.kotlincountrieslist.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlincountrieslist.databinding.ItemCountryBinding
import com.example.kotlincountrieslist.model.CountryModel

class CountryAdapter(val countryList: ArrayList<CountryModel>):RecyclerView.Adapter<CountryAdapter.CountryHolder>() {

    class CountryHolder(val binding: ItemCountryBinding):RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryHolder {
        var binding = ItemCountryBinding.inflate(LayoutInflater.from(parent.context))
        return CountryHolder(binding)
    }

    override fun onBindViewHolder(holder: CountryHolder, position: Int) {
        holder.binding.name.text = countryList.get(position).CountryName
        holder.binding.region.text = countryList.get(position).CountryRegion
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    //@SuppressLint("NotifyDataSetChanged")
    fun updateCountryList(newCountryList:List<CountryModel>){
        countryList.clear()
        countryList.addAll(newCountryList)
        notifyDataSetChanged()
    }
}