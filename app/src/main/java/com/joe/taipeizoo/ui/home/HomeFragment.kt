package com.joe.taipeizoo.ui.home

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.joe.taipeizoo.R
import com.joe.taipeizoo.adapter.FieldListAdapter
import com.joe.taipeizoo.databinding.FragmentHomeBinding
import org.koin.android.ext.android.inject

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: FieldListAdapter
    private lateinit var repository: HomeRepository

    companion object {
        val TAG = HomeFragment :: class.java.simpleName
    }
    private val homeViewModel: HomeViewModel by inject()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        repository = HomeRepository()

        binding.viewModel = homeViewModel
        binding.lifecycleOwner = this

        val cm = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true

        homeViewModel.setNetworkConnected(isConnected)

        binding.recycler.layoutManager = LinearLayoutManager(context)
        binding.recycler.setHasFixedSize(true)

        homeViewModel.results.observe(viewLifecycleOwner, Observer {
            adapter = FieldListAdapter(homeViewModel)
            binding.recycler.adapter = adapter
            adapter.notifyDataSetChanged()
        })

        homeViewModel.clickItem.observe(viewLifecycleOwner, Observer {
            if (it != null)
            {
                findNavController().navigate(HomeFragmentDirections.actionToSecond(it))
                homeViewModel.clearClickItem()
            }
        })

        homeViewModel.isConnected.observe(viewLifecycleOwner, Observer {
            if(it == false)
            {
                Toast.makeText(context, getString(R.string.network_error), Toast.LENGTH_SHORT).show()
            }
        })

        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.title_home)

        return binding.root
    }
}