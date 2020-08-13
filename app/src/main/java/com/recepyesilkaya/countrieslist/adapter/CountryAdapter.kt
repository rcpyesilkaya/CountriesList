package com.recepyesilkaya.countrieslist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.recepyesilkaya.countrieslist.R
import com.recepyesilkaya.countrieslist.model.CountryModel
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
    }

    fun updateCountryList(newCountryList:ArrayList<CountryModel>){
        countryList.clear()
        countryList.addAll(newCountryList)
        notifyDataSetChanged()
    }
}