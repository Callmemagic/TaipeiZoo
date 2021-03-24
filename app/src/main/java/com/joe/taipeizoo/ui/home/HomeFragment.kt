package com.joe.taipeizoo.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.joe.taipeizoo.R
import com.joe.taipeizoo.adapter.FieldListAdapter
import com.joe.taipeizoo.databinding.FragmentHomeBinding
import com.joe.taipeizoo.ui.field.FieldFragmentDirections

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: FieldListAdapter
    companion object {
        val TAG = HomeFragment :: class.java.simpleName
    }
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)

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

        return binding.root
    }
}