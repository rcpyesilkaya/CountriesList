package com.recepyesilkaya.countrieslist

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_feed.*


class FeedFragment : Fragment() {
    private var countryUuid=1;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        feedBtn.setOnClickListener {
            val action=FeedFragmentDirections.actionFeedFragmentToCountryFragment(countryUuid)
            Navigation.findNavController(it).navigate(action)
        }

    }
}