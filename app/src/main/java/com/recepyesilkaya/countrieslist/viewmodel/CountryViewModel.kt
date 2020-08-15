package com.recepyesilkaya.countrieslist.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.recepyesilkaya.countrieslist.model.CountryModel
import com.recepyesilkaya.countrieslist.service.CountryDatabase
import kotlinx.coroutines.launch

class CountryViewModel(application: Application): BaseViewModel(application){

    val countryList=MutableLiveData<CountryModel>()

    fun getDataFromRoom(uuid:Int){
        launch {
            val dao=CountryDatabase(getApplication()).countryDao()
            val country=dao.getCountry(uuid)
            countryList.value=country
        }
    }
}