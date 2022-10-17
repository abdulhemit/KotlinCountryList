package com.example.kotlincountrieslist.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlincountrieslist.model.CountryModel
import com.example.kotlincountrieslist.service.CountryDatabase
import com.example.kotlincountrieslist.util.CustomSharedPreferences
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class FeedViewModel(application: Application):BaseViewModel(application) {

    private val countryAPIService = com.example.kotlincountrieslist.service.countryAPIService()
    private val disposable = CompositeDisposable()
    private val customPreferences = CustomSharedPreferences(getApplication())

    val countries = MutableLiveData<List<CountryModel>>()
    val countryError = MutableLiveData<Boolean>()
    val countryLoading = MutableLiveData<Boolean>()


    fun refreshData(){
        getDataFromAPI()

    }
    fun getDataFromAPI(){
        countryLoading.value = true
        disposable.add(
            countryAPIService.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<CountryModel>>(){
                    override fun onSuccess(t: List<CountryModel>) {
                        storeSQLite(t)
                    }

                    override fun onError(e: Throwable) {
                        countryLoading.value = false
                        countryError.value = true
                        e.printStackTrace()

                    }

                })
        )

    }
    private fun showCountries(countryList: List<CountryModel>){
        countries.value = countryList
        countryError.value = false
        countryLoading.value = false
    }
    private fun storeSQLite(list: List<CountryModel>){
        launch {
            val dao = CountryDatabase(getApplication()).countryDao()
            dao.getAllCountries()
            val listLong = dao.insertAll(*list.toTypedArray())

            var i =  0

            while (i < list.size){
                list[i].uuid = listLong[i].toInt()
                i = i + 1
            }
            showCountries(list)
        }
        customPreferences.saveTime(System.nanoTime())
    }

}