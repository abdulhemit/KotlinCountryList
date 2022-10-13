package com.example.kotlincountrieslist.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlincountrieslist.databinding.ItemCountryBinding
import com.example.kotlincountrieslist.model.CountryModel
import com.example.kotlincountrieslist.util.DownloadFromUrl
import com.example.kotlincountrieslist.util.plaseholderProgressBar
import com.example.kotlincountrieslist.view.FeedFragmentDirections

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
        holder.itemView.setOnClickListener {
            val action = FeedFragmentDirections.actionFeedFragmentToCountryFragment()
            Navigation.findNavController(it).navigate(action)
        }
        holder.binding.imageView.DownloadFromUrl(countryList[position].imageUrl!!,
            plaseholderProgressBar(holder.itemView.context))
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