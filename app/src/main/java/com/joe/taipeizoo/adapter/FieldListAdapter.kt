package com.joe.taipeizoo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.joe.taipeizoo.R
import com.joe.taipeizoo.bean.Field.ResultX
import com.joe.taipeizoo.databinding.FieldItemBinding
import com.joe.taipeizoo.ui.home.HomeFragment

/**
 * author: Joe Cheng
 */
class FieldListAdapter(var fields: List<ResultX>) : RecyclerView.Adapter<FieldListViewHolder>(){
    private lateinit var binding: FieldItemBinding
    private lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FieldListViewHolder {
        context = parent.context
        binding = FieldItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return FieldListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FieldListViewHolder, position: Int) {
        holder.title.text = fields.get(position).E_Name
        holder.content.text = fields.get(position).E_Info
        holder.memo.text = fields.get(position).E_Memo
        Glide.with(context)
            .load(fields.get(position).E_Pic_URL)
            .error(R.mipmap.ic_launcher)
            .apply( RequestOptions().override(400, 400))
            .centerCrop()
            .into(holder.fieldImg)
        holder.item.setOnClickListener {

        }
    }

    override fun getItemCount(): Int {
        return fields.size
    }
}

class FieldListViewHolder(@NonNull view: FieldItemBinding) : RecyclerView.ViewHolder(view.root)
{
    var fieldImg = view.ivField
    var title = view.tvTitle
    var content = view.tvContent
    var memo = view.tvMemo
    var item = view.clItem
}