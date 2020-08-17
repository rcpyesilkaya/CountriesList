package com.recepyesilkaya.countrieslist.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.recepyesilkaya.countrieslist.R
import com.recepyesilkaya.countrieslist.adapter.CountryAdapter
import com.recepyesilkaya.countrieslist.databinding.FragmentCountryBinding
import com.recepyesilkaya.countrieslist.util.downloadImageFromUrl
import com.recepyesilkaya.countrieslist.util.getPlaceHolder
import com.recepyesilkaya.countrieslist.viewmodel.CountryViewModel
import kotlinx.android.synthetic.main.fragment_country.*


class CountryFragment : Fragment() {

    private lateinit var countryViewModel:CountryViewModel

    private var countryUuidC=0

    private lateinit var dataBinding:FragmentCountryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        dataBinding=DataBindingUtil.inflate(inflater,R.layout.fragment_country,container,false)

        return dataBinding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        arguments?.let {
            countryUuidC=CountryFragmentArgs.fromBundle(it).countryUuid
        }
        countryViewModel=ViewModelProviders.of(this).get(CountryViewModel::class.java)
        countryViewModel.getDataFromRoom(countryUuidC)

        obserLiveDataCountry()
    }
     fun obserLiveDataCountry(){
         countryViewModel.countryList.observe(viewLifecycleOwner, Observer { countries->
             countries?.let {

                 dataBinding.selectedCountry=it
               /*  detailCountryCapital.text=it.countryCapital
                 detailCountryCurrency.text=it.countryCurrency
                 detailCountryLanguage.text=it.countryLanguage
                 detailCountryName.text=it.countryName
                 detailCountryRegion.text=it.countryRegion
                 context?.let {
                     detailImg.downloadImageFromUrl(countries.countryImageUrl, getPlaceHolder(it))
                 }*/

             }
         })
     }
}
