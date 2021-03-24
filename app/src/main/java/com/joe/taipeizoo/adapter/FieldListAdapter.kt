package com.joe.taipeizoo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.joe.taipeizoo.bean.field.FieldDetailResult
import com.joe.taipeizoo.databinding.FieldItemBinding
import com.joe.taipeizoo.ui.home.HomeViewModel

/**
 * author: Joe Cheng
 */
class FieldListAdapter(private val viewModel: HomeViewModel) :
    RecyclerView.Adapter<FieldListViewHolder>(){
    var fields : List<FieldDetailResult>? = viewModel.results.value

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FieldListViewHolder {
        return FieldListViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: FieldListViewHolder, position: Int) {
        val item = fields!![position]
        holder.bind(viewModel, item)
    }

    override fun getItemCount(): Int {
        return fields?.size ?: 0
    }
}

class FieldListViewHolder(private val binding: FieldItemBinding) : RecyclerView.ViewHolder(binding.root)
{
    fun bind(viewModel: HomeViewModel, item: FieldDetailResult)
    {
        binding.viewModel = viewModel
        binding.result = item
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): FieldListViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = FieldItemBinding.inflate(layoutInflater, parent, false)

            return FieldListViewHolder(binding)
        }
    }
}