package com.recepyesilkaya.countrieslist.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.recepyesilkaya.countrieslist.R
import com.recepyesilkaya.countrieslist.databinding.CountryRowBinding
import com.recepyesilkaya.countrieslist.model.CountryModel
import com.recepyesilkaya.countrieslist.util.downloadImageFromUrl
import com.recepyesilkaya.countrieslist.util.getPlaceHolder
import com.recepyesilkaya.countrieslist.view.FeedFragmentDirections
import kotlinx.android.synthetic.main.country_row.view.*


class CountryAdapter(private val countryList: ArrayList<CountryModel>) :
    RecyclerView.Adapter<CountryAdapter.CountryViewHolder>(),CountryClickListener {

    class CountryViewHolder(var view: CountryRowBinding) : RecyclerView.ViewHolder(view.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        // val view=inflater.inflate(R.layout.country_row,parent,false)
        val view=DataBindingUtil.inflate<CountryRowBinding>(inflater,R.layout.country_row,parent,false)
        return CountryViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return countryList.size
    }


    fun updateCountryList(newCountryList: ArrayList<CountryModel>) {
        countryList.clear()
        countryList.addAll(newCountryList)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {

        holder.view.countryModel=countryList[position]
        holder.view.listener=this
        /*holder.itemView.countryName.text = countryList[position].countryName
        holder.itemView.countryRegion.text = countryList[position].countryRegion

        holder.itemView.countryImg.downloadImageFromUrl(
            countryList[position].countryImageUrl,
            getPlaceHolder(holder.itemView.context)
        )

        holder.itemView.setOnClickListener {
            val action =
                FeedFragmentDirections.actionFeedFragmentToCountryFragment(countryList[position].uuid)
            Navigation.findNavController(it).navigate(action)
        }*/

    }

    override fun onCountryClicked(view: View) {
        val uuid=view.countryUuidText.text.toString().toInt()

        val action =
            FeedFragmentDirections.actionFeedFragmentToCountryFragment(uuid)
        Navigation.findNavController(view).navigate(action)
    }
}