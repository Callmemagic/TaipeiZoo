package com.joe.taipeizoo.ui.field

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.joe.taipeizoo.adapter.AnimalListAdapter
import com.joe.taipeizoo.databinding.FragmentFieldInfoBinding

class FieldFragment : Fragment() {
    private lateinit var binding : FragmentFieldInfoBinding
    private lateinit var fieldViewModel:  FieldViewModel
    private lateinit var adapter : AnimalListAdapter

    private val args by navArgs<FieldFragmentArgs>()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFieldInfoBinding.inflate(inflater, container, false)
        fieldViewModel =
                ViewModelProvider(this).get(FieldViewModel::class.java)

        fieldViewModel.setItem(args.fieldInfo)

        binding.recycler.layoutManager = LinearLayoutManager(context)
        binding.recycler.setHasFixedSize(true)

        fieldViewModel.results.observe(viewLifecycleOwner, Observer {
            adapter = AnimalListAdapter(fieldViewModel)
            binding.recycler.adapter = adapter
            adapter.notifyDataSetChanged()
        })

        fieldViewModel.clickItem.observe(viewLifecycleOwner, Observer {
            if (it != null)
            {
                findNavController().navigate(FieldFragmentDirections.actionToThird(it))
                fieldViewModel.clearClickItem()
            }
        })

        return binding.root
    }
}