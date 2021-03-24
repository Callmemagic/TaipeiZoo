package com.joe.taipeizoo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.joe.taipeizoo.bean.animals.AnimalDetailResult
import com.joe.taipeizoo.bean.field.FieldDetailResult
import com.joe.taipeizoo.databinding.AnimalItemBinding
import com.joe.taipeizoo.databinding.FieldItemBinding
import com.joe.taipeizoo.ui.dashboard.SecondInfoViewModel
import com.joe.taipeizoo.ui.home.HomeViewModel

/**
 * author: Joe Cheng
 */
class AnimalListAdapter(private val viewModel: SecondInfoViewModel) :
    RecyclerView.Adapter<AnimalListViewHolder>(){
    var animals : List<AnimalDetailResult>? = viewModel.results.value

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalListViewHolder {
        return AnimalListViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: AnimalListViewHolder, position: Int) {
        val animal = animals!![position]
        holder.bind(viewModel, animal)
    }

    override fun getItemCount(): Int {
        return animals?.size ?: 0
    }
}

class AnimalListViewHolder(private val binding: AnimalItemBinding) : RecyclerView.ViewHolder(binding.root)
{
    fun bind(viewModel: SecondInfoViewModel, item: AnimalDetailResult)
    {
        binding.viewModel = viewModel
        binding.result = item
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): AnimalListViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = AnimalItemBinding.inflate(layoutInflater, parent, false)

            return AnimalListViewHolder(binding)
        }
    }
}