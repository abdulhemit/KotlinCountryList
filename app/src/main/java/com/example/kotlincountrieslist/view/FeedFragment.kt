package com.example.kotlincountrieslist.view

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlincountrieslist.adapter.CountryAdapter
import com.example.kotlincountrieslist.databinding.FragmentFeedBinding
import com.example.kotlincountrieslist.viewmodel.FeedViewModel


class FeedFragment : Fragment() {
    private var _binding: FragmentFeedBinding? = null
    private lateinit var viewModel: FeedViewModel
    private var countryAdapter = CountryAdapter(arrayListOf())
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFeedBinding.inflate(inflater, container, false)

        //viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(application = Application()).create(FeedViewModel::class.java)
        //viewModel = ViewModelProvider(this).get(FeedViewModel::class.java)
        viewModel = ViewModelProviders.of(this).get(FeedViewModel::class.java)
        viewModel.refreshData()

        binding.countryList.layoutManager = LinearLayoutManager(requireContext())
        binding.countryList.adapter = countryAdapter

        observeLiveData()
        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.swipeRefreshLayout.isRefreshing = false
            binding.countryList.visibility = View.GONE
            binding.countryError.visibility = View.GONE
            binding.countryLoading.visibility = View.VISIBLE
            viewModel.refreshData()

        }
        /*
        val myString = "James"
        myString.myEctension(" hetfield")

f
         */

        /*
        binding.buttonFeed.setOnClickListener {
            val action = FeedFragmentDirections.actionFeedFragmentToCountryFragment()
            Navigation.findNavController(it).navigate(action)
        }

         */
        val view = binding.root
        return view
    }
    fun observeLiveData(){
        viewModel.countries.observe(viewLifecycleOwner, Observer {
            it?.let {
                binding.countryList.visibility = View.VISIBLE
                countryAdapter.updateCountryList(it)
            }
        })
        viewModel.countryError.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it){
                    binding.countryError.visibility = View.VISIBLE
                }else {
                    binding.countryError.visibility = View.INVISIBLE
                }
            }
        })
        viewModel.countryLoading.observe(viewLifecycleOwner, Observer { 
            it?.let { 
                if (it){
                    binding.countryLoading.visibility = View.VISIBLE
                    binding.countryList.visibility = View.GONE
                    binding.countryError.visibility = View.GONE
                }
                else{
                    binding.countryLoading.visibility = View.GONE

                }
            }
        })
    }

}