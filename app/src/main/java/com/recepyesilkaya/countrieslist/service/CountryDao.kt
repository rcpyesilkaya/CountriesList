package com.recepyesilkaya.countrieslist.service

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.recepyesilkaya.countrieslist.model.CountryModel

@Dao
interface CountryDao {

    @Insert
    suspend fun insertAll(vararg country:CountryModel):List<Long>

    @Query("SELECT * FROM countryModel")
    suspend fun getAllCountries():List<CountryModel>

    @Query("SELECT * FROM countrymodel WHERE uuid=:uuid")
    suspend fun getCountry(uuid:Int):CountryModel

    @Query("DELETE FROM countryModel")
    suspend fun deleteAllCounties()

}