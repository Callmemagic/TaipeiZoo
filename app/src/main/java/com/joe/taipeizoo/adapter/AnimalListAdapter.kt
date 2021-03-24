package com.joe.taipeizoo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.joe.taipeizoo.bean.animals.AnimalDetailResult
import com.joe.taipeizoo.bean.field.FieldDetailResult
import com.joe.taipeizoo.databinding.AnimalItemBinding
import com.joe.taipeizoo.databinding.FieldDetailBinding
import com.joe.taipeizoo.ui.field.FieldViewModel

/**
 * author: Joe Cheng
 */
class AnimalListAdapter(private val viewModel: FieldViewModel) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    var animals : List<AnimalDetailResult>? = viewModel.results.value
    var fieldInfo : FieldDetailResult? = viewModel.itemClicked.value

    val TYPE_HEADER = 0
    val TYPE_NORMAL = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == TYPE_NORMAL)
        {
            return AnimalListViewHolder.from(parent)
        }
        else if(viewType == TYPE_HEADER)
        {
            return FieldInfoViewHolder.from(parent)
        }
        return AnimalListViewHolder.from(parent)
    }

    override fun getItemCount(): Int {
        return (animals?.size ?: 0) + 1
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is AnimalListViewHolder)
        {
            val animal = animals!![position]
            holder.bind(viewModel, animal)
        }
        else if (holder is FieldInfoViewHolder)
        {
            val field = fieldInfo!!
            holder.bind(viewModel, field)
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0) return TYPE_HEADER
        else return TYPE_NORMAL
    }
}

class AnimalListViewHolder(private val binding: AnimalItemBinding) : RecyclerView.ViewHolder(binding.root)
{
    fun bind(viewModel: FieldViewModel, item: AnimalDetailResult)
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

class FieldInfoViewHolder(private val binding : FieldDetailBinding) : RecyclerView.ViewHolder(binding.root)
{
    fun bind(viewModel : FieldViewModel, item : FieldDetailResult)
    {
        binding.viewModel = viewModel
        binding.fieldInfo = item
    }

    companion object {
        fun from(parent: ViewGroup): FieldInfoViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = FieldDetailBinding.inflate(layoutInflater, parent, false)

            return FieldInfoViewHolder(binding)
        }
    }
}