package com.recepyesilkaya.countrieslist

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_country.*
import kotlinx.android.synthetic.main.fragment_feed.*


class CountryFragment : Fragment() {

    private var countryUuidC=0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_country, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        arguments?.let {
            countryUuidC=CountryFragmentArgs.fromBundle(it).countryUuid
            countryTxt.text=countryUuidC.toString()
        }

        countryBtn.setOnClickListener {
            val action=CountryFragmentDirections.actionCountryFragmentToFeedFragment()
            Navigation.findNavController(it).navigate(action)
        }

    }

}
