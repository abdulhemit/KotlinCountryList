package com.example.kotlincountrieslist.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.kotlincountrieslist.R
import com.example.kotlincountrieslist.databinding.FragmentCountryBinding
import com.example.kotlincountrieslist.viewmodel.CountryViewModel

class CountryFragment : Fragment() {
    private var _binding : FragmentCountryBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: CountryViewModel
    private var countryUuid = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       _binding = FragmentCountryBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(CountryViewModel::class.java)
        viewModel.getDataFromRoom()
        observeLiveData()


        arguments?.let {
            countryUuid = CountryFragmentArgs.fromBundle(it).countryUuid
        }
    }
    fun observeLiveData(){
        viewModel.CountryLiveData.observe(viewLifecycleOwner, Observer {
            it?.let {
                binding.countryName.text = it.CountryName
                binding.countryCapital.text = it.CountryCapital
                binding.countryCurrency.text = it.CountryCurrency
                binding.countryRegion.text = it.CountryRegion
                binding.countryLanguage.text = it.CountryLanguage
            }
        })
    }


}