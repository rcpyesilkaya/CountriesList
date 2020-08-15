package com.recepyesilkaya.countrieslist.util

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.preference.PreferenceManager
import androidx.preference.PreferenceScreen
import kotlinx.coroutines.channels.ticker

class CustomSharedPreferences {

    companion object {

        private val SHAREDPF="time"

        private var sharedPreferences: SharedPreferences? = null

        @Volatile
        private var instance: CustomSharedPreferences? = null

        private val lock = Any()

        operator fun invoke(context: Context): CustomSharedPreferences =
            instance ?: synchronized(lock) {
                instance ?:makeSharedPreferences(context)

            }

        private fun makeSharedPreferences(context: Context): CustomSharedPreferences {

            sharedPreferences=PreferenceManager.getDefaultSharedPreferences(context)
            return CustomSharedPreferences()
        }
    }

    fun saveTime(time:Long){
        sharedPreferences?.edit(commit=true){
            putLong(SHAREDPF,time)
        }
    }
    fun getTime()= sharedPreferences?.getLong(SHAREDPF, 0)
}