package com.recepyesilkaya.countrieslist.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.recepyesilkaya.countrieslist.model.CountryModel
import com.recepyesilkaya.countrieslist.service.CountryAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class FeedViewModel : ViewModel() {

    private val countryApiService = CountryAPIService()
    private val disposable = CompositeDisposable()


    val countries = MutableLiveData<List<CountryModel>>()
    val countryError = MutableLiveData<Boolean>()
    val countryLoading = MutableLiveData<Boolean>()

    fun refreshData() {
        getDataFromAPI()
    }

    fun getDataFromAPI() {

        countryLoading.value = true

        disposable.add(countryApiService.getData()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<List<CountryModel>>() {
                override fun onSuccess(t: List<CountryModel>) {
                    countries.value = t
                    countryLoading.value = false
                    countryError.value = false
                }

                override fun onError(e: Throwable) {
                    countryError.value = true
                    countryLoading.value = false
                    e.printStackTrace()
                }
            }

            )
        )
    }
}