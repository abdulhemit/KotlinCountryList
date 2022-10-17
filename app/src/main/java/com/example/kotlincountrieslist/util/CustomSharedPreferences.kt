package com.example.kotlincountrieslist.util

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import java.util.prefs.Preferences

class CustomSharedPreferences {


    companion object{
        private val PREFERENCES_TIME = "Preference_time"
        private var sharedPreferences : SharedPreferences? = null

        @Volatile private var instance : CustomSharedPreferences? = null
        private val lock = Any()
        operator fun invoke(context: Context) : CustomSharedPreferences = instance ?: synchronized(lock){
            instance ?: makeCustomSharedPreferences(context).also {
                instance = it
            }
        }
        private fun makeCustomSharedPreferences(context: Context): CustomSharedPreferences{
            sharedPreferences = androidx.preference.PreferenceManager.getDefaultSharedPreferences(context)
            ///sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
            return CustomSharedPreferences()
        }
    }
     fun saveTime(time : Long){
        sharedPreferences?.edit()?.putLong(PREFERENCES_TIME,time)
    }

}