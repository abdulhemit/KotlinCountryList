package com.example.kotlincountrieslist.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class CountryModel(

    @ColumnInfo(name = "name")
    @SerializedName("name")
    val CountryName: String?,

    @ColumnInfo(name = "capital")
    @SerializedName("capital")
    val CountryCapital: String?,

    @ColumnInfo(name = "region")
    @SerializedName("region")
    val CountryRegion: String?,

    @ColumnInfo(name = "currency")
    @SerializedName("currency")
    val CountryCurrency: String?,

    @ColumnInfo(name = "language")
    @SerializedName("language")
    val CountryLanguage: String?,

    @ColumnInfo(name = "flag")
    @SerializedName("flag")
    val imageUrl:String?
){
    @PrimaryKey(autoGenerate = true)
    var uuid : Int = 0
}