package com.joe.taipeizoo.ui.field

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
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
    private lateinit var fieldRepository: FieldRepository

    private val args by navArgs<FieldFragmentArgs>()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFieldInfoBinding.inflate(inflater, container, false)

        fieldRepository = FieldRepository()
        fieldViewModel =
            ViewModelProvider(this, FieldViewModelFactory(fieldRepository)).get(FieldViewModel::class.java)
        binding.lifecycleOwner = this

        val cm = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true

        fieldViewModel.setItem(args.fieldInfo)
        fieldViewModel.setNetworkConnected(isConnected)

        binding.recycler.layoutManager = LinearLayoutManager(context)
        binding.recycler.setHasFixedSize(true)

        fieldViewModel.results.observe(viewLifecycleOwner, Observer {
            adapter = AnimalListAdapter(fieldViewModel)
            binding.recycler.adapter = adapter
            adapter.notifyDataSetChanged()
        })

        fieldViewModel.animalClicked.observe(viewLifecycleOwner, Observer {
            if (it != null)
            {
                findNavController().navigate(FieldFragmentDirections.actionToThird(it))
                fieldViewModel.clearClickItem()
            }
        })

        (activity as AppCompatActivity).supportActionBar?.title = args.fieldInfo.E_Name

        fieldViewModel.onClickOpenWeb.observe(viewLifecycleOwner, Observer {
            if(it != null)
            {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(it)
                startActivity(intent)
                fieldViewModel.clearOpenWebUrl()
            }
        })

        return binding.root
    }
}