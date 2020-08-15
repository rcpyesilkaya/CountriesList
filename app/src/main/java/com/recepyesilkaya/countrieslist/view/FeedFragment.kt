package com.recepyesilkaya.countrieslist.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.recepyesilkaya.countrieslist.R
import com.recepyesilkaya.countrieslist.adapter.CountryAdapter
import com.recepyesilkaya.countrieslist.model.CountryModel
import com.recepyesilkaya.countrieslist.util.CustomSharedPreferences
import com.recepyesilkaya.countrieslist.viewmodel.FeedViewModel
import kotlinx.android.synthetic.main.fragment_feed.*


class FeedFragment : Fragment() {

    private lateinit var feedViewModel: FeedViewModel
    private var feedAdapter = CountryAdapter(arrayListOf())

    private var countryUuid = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        feedViewModel = ViewModelProviders.of(this).get(FeedViewModel::class.java)
        feedViewModel.refreshData()

        countryRcy.layoutManager = LinearLayoutManager(context)
        countryRcy.adapter = feedAdapter

        swipeRefresh.setOnRefreshListener {
            swipeRefresh.isRefreshing=false

            countryRcy.visibility=View.GONE
            loadingProgress.visibility=View.VISIBLE
            errorTxt.visibility=View.GONE

            feedViewModel.refreshFromAPI()

        }


        obserLiveData()
    }

    fun obserLiveData() {


        feedViewModel.countries.observe(viewLifecycleOwner, Observer { countries ->
            countries?.let {
                countryRcy.visibility = View.VISIBLE
                feedAdapter.updateCountryList(it as ArrayList<CountryModel>)
            }
        })

        feedViewModel.countryError.observe(viewLifecycleOwner, Observer { error ->
            error?.let {
                if (it) {
                    errorTxt.visibility = View.VISIBLE
                    countryRcy.visibility = View.GONE
                    loadingProgress.visibility = View.GONE
                } else {
                    errorTxt.visibility = View.GONE
                }
            }
        })

        feedViewModel.countryLoading.observe(viewLifecycleOwner, Observer { loading ->
            loading?.let {
                if (it) {
                    loadingProgress.visibility = View.VISIBLE
                    errorTxt.visibility = View.GONE
                    countryRcy.visibility = View.GONE
                } else {
                    loadingProgress.visibility = View.GONE
                }
            }
        })


    }
}
