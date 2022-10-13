package com.example.kotlincountrieslist.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.kotlincountrieslist.model.CountryModel

@Dao
interface CountryDao {

    //Data Access Object


    @Insert
    suspend fun insertAll(vararg countries : CountryModel) : List<Long>

    // insert -> Insert Into
    // suspend -> corountine, pause & resume
    // vararg -> mutliple country objects
    // List<Long> -> primary keys


    @Query("SELECT * FROM CountryModel")
    suspend fun getAllCountries() : List<CountryModel>

    @Query("SELECT * FROM CountryModel WHERE uuid = :countryId")
    suspend fun getCountry(countryId : Int) : CountryModel

    @Query("DELETE  FROM countrymodel")
    suspend fun deleteAllCountries()

}