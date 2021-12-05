package com.l3azh.l3azhapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.l3azh.l3azhapp.api.res.GetCTDTItemResData
import com.l3azh.l3azhapp.databinding.CtdtRvItemBinding

class CTDTAdapter(val listener: CTDTListner):RecyclerView.Adapter<CTDTViewHolder>() {

    var diffCallBack = object : DiffUtil.ItemCallback<GetCTDTItemResData>(){
        override fun areItemsTheSame(
            oldItem: GetCTDTItemResData,
            newItem: GetCTDTItemResData
        ): Boolean {
            return oldItem.codeSubject == newItem.codeSubject
        }

        override fun areContentsTheSame(
            oldItem: GetCTDTItemResData,
            newItem: GetCTDTItemResData
        ): Boolean {
            return oldItem == newItem
        }
    }

    var differ = AsyncListDiffer(this, diffCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CTDTViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding:CtdtRvItemBinding =
            CtdtRvItemBinding.inflate(layoutInflater, parent, false)
        return CTDTViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CTDTViewHolder, position: Int) {
        holder.binding(differ.currentList.get(position), listener)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    interface CTDTListner{
        fun onItemClickListener(item: GetCTDTItemResData)
    }
}