package com.recepyesilkaya.countrieslist.service

import com.recepyesilkaya.countrieslist.model.CountryModel
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET

interface CountryAPI {

    //https://raw.githubusercontent.com/atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json
    @GET("atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json")
    fun getCountry():Single<List<CountryModel>>
}