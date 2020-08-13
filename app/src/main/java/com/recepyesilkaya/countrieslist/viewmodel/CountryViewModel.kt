package com.recepyesilkaya.countrieslist.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.recepyesilkaya.countrieslist.model.CountryModel

class CountryViewModel: ViewModel() {

    val countryList=MutableLiveData<CountryModel>()

    fun getDataFromRoom(){
        val country=CountryModel("turkey","asda","asdas","safa","asf","fa")
        countryList.value=country
    }

}