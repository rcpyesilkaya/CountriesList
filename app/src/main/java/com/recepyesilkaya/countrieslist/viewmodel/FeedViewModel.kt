package com.recepyesilkaya.countrieslist.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.recepyesilkaya.countrieslist.model.CountryModel
import com.recepyesilkaya.countrieslist.service.CountryAPIService
import com.recepyesilkaya.countrieslist.service.CountryDatabase
import com.recepyesilkaya.countrieslist.util.CustomSharedPreferences
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class FeedViewModel(application: Application) : BaseViewModel(application) {

    private val countryApiService = CountryAPIService()
    private val disposable = CompositeDisposable()
    private var sharedPreferences = CustomSharedPreferences(getApplication())
    private val refreshTime = 10 * 60 * 1000 * 1000 * 1000L


    val countries = MutableLiveData<List<CountryModel>>()
    val countryError = MutableLiveData<Boolean>()
    val countryLoading = MutableLiveData<Boolean>()

    fun refreshData() {

        val updateTime = sharedPreferences.getTime()

        if (updateTime != null && updateTime != 0L && System.nanoTime() - updateTime < refreshTime) {
            getDataFromSql()
        } else {
            getDataFromAPI()
        }
    }
    fun refreshFromAPI(){
        getDataFromAPI()
    }

    private fun getDataFromSql() {

        countryLoading.value = true

        launch {
            val countriesList = CountryDatabase(getApplication()).countryDao().getAllCountries()
            showElement(countriesList)
            Toast.makeText(getApplication(),"SQL",Toast.LENGTH_LONG).show()
        }

    }

    fun getDataFromAPI() {

        countryLoading.value = true

        disposable.add(countryApiService.getData()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<List<CountryModel>>() {
                override fun onSuccess(t: List<CountryModel>) {
                    storeInSqllite(t)
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

    fun showElement(countriesList: List<CountryModel>) {
        countries.value = countriesList
        countryLoading.value = false
        countryError.value = false
    }

    fun storeInSqllite(countriesList: List<CountryModel>) {

        launch {
            val dao = CountryDatabase(getApplication()).countryDao()
            dao.deleteAllCounties()
            val listLong = dao.insertAll(*countriesList.toTypedArray())
            var i = 0
            while (i < countriesList.size) {
                countriesList[i].uuid = listLong[i].toInt()
                i++
            }
            showElement(countriesList)
        }
        sharedPreferences.saveTime(System.nanoTime())

        Toast.makeText(getApplication(),"API",Toast.LENGTH_LONG).show()

    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}