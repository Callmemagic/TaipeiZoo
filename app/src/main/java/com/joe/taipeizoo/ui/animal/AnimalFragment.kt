package com.joe.taipeizoo.ui.animal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.joe.taipeizoo.databinding.FragmentAnimalInfoBinding

class AnimalFragment : Fragment() {
    private lateinit var animalViewModel: AnimalViewModel
    private lateinit var binding : FragmentAnimalInfoBinding

    private val args by navArgs<AnimalFragmentArgs>()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAnimalInfoBinding.inflate(inflater, container, false)

        animalViewModel =
                ViewModelProvider(this).get(AnimalViewModel::class.java)

        binding.viewModel = animalViewModel
        binding.animalInfo = args.animalInfo
        binding.lifecycleOwner = this

        (activity as AppCompatActivity).supportActionBar?.title = args.animalInfo.A_Name_Ch

        return binding.root
    }
}