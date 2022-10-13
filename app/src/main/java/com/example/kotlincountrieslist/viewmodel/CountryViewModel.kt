package com.example.kotlincountrieslist.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlincountrieslist.model.CountryModel

class CountryViewModel: ViewModel() {

    val CountryLiveData = MutableLiveData<CountryModel>()

    fun getDataFromRoom(){
        val country = CountryModel("Turkey","Asia","Ankara","TRY","Turkish","ww.ss.com")
        CountryLiveData.value = country
    }

}