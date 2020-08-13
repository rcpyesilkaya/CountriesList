package com.recepyesilkaya.countrieslist.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.recepyesilkaya.countrieslist.R
import com.recepyesilkaya.countrieslist.adapter.CountryAdapter
import com.recepyesilkaya.countrieslist.viewmodel.CountryViewModel
import kotlinx.android.synthetic.main.fragment_country.*


class CountryFragment : Fragment() {

    private lateinit var countryViewModel:CountryViewModel

    private var countryUuidC=0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_country, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        countryViewModel=ViewModelProviders.of(this).get(CountryViewModel::class.java)
        countryViewModel.getDataFromRoom()


        obserLiveDataCountry()
    }
     fun obserLiveDataCountry(){
         countryViewModel.countryList.observe(viewLifecycleOwner, Observer { countries->
             countries?.let {
                 detailCountryCapital.text=it.countryCapital
                 detailCountryCurrency.text=it.countryCurrency
                 detailCountryLanguage.text=it.countryLanguage
                 detailCountryName.text=it.countryName
                 detailCountryRegion.text=it.countryRegion
             }
         })
     }
}
