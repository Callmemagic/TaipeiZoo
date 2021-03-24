package com.joe.taipeizoo.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.joe.taipeizoo.R
import com.joe.taipeizoo.adapter.AnimalListAdapter
import com.joe.taipeizoo.adapter.FieldListAdapter
import com.joe.taipeizoo.databinding.FragmentHomeBinding
import com.joe.taipeizoo.databinding.FragmentSecondInfoBinding

class SecondInfoFragment : Fragment() {
    private lateinit var binding : FragmentSecondInfoBinding
    private lateinit var secondInfoViewModel:  SecondInfoViewModel
    private lateinit var adapter : AnimalListAdapter

    private val args by navArgs<SecondInfoFragmentArgs>()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSecondInfoBinding.inflate(inflater, container, false)
        secondInfoViewModel =
                ViewModelProvider(this).get(SecondInfoViewModel::class.java)

        secondInfoViewModel.setItem(args.fieldInfo)

        binding.recycler.layoutManager = LinearLayoutManager(context)
        binding.recycler.setHasFixedSize(true)

        secondInfoViewModel.results.observe(viewLifecycleOwner, Observer {

            adapter = AnimalListAdapter(secondInfoViewModel)
            binding.recycler.adapter = adapter
            adapter.notifyDataSetChanged()
        })

        return binding.root
    }
}