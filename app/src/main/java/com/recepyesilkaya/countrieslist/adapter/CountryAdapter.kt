package com.recepyesilkaya.countrieslist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.recepyesilkaya.countrieslist.R
import com.recepyesilkaya.countrieslist.model.CountryModel
import com.recepyesilkaya.countrieslist.util.downloadImageFromUrl
import com.recepyesilkaya.countrieslist.util.getPlaceHolder
import com.recepyesilkaya.countrieslist.view.FeedFragment
import com.recepyesilkaya.countrieslist.view.FeedFragmentDirections
import kotlinx.android.synthetic.main.country_row.view.*

class CountryAdapter(val countryList:ArrayList<CountryModel>):RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    class CountryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val inflater=LayoutInflater.from(parent.context)
        val view=inflater.inflate(R.layout.country_row,parent,false)
        return CountryViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return  countryList.size
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.itemView.countryName.text=countryList[position].countryName
        holder.itemView.countryRegion.text=countryList[position].countryRegion

        holder.itemView.countryImg.downloadImageFromUrl(countryList[position].countryImageUrl,getPlaceHolder(holder.itemView.context))

        holder.itemView.setOnClickListener {
            val action=FeedFragmentDirections.actionFeedFragmentToCountryFragment(countryList[position].uuid)
            Navigation.findNavController(it).navigate(action)
        }
    }

    fun updateCountryList(newCountryList:ArrayList<CountryModel>){
        countryList.clear()
        countryList.addAll(newCountryList)
        notifyDataSetChanged()
    }
}