package com.l3azh.l3azhapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.l3azh.l3azhapp.api.res.GetPointItemResData
import com.l3azh.l3azhapp.databinding.PointRvItemBinding

class PointAdapter:RecyclerView.Adapter<PointViewHolder>() {

    var diffCallBack = object : DiffUtil.ItemCallback<GetPointItemResData>(){
        override fun areItemsTheSame(
            oldItem: GetPointItemResData,
            newItem: GetPointItemResData
        ): Boolean {
            return false
        }

        override fun areContentsTheSame(
            oldItem: GetPointItemResData,
            newItem: GetPointItemResData
        ): Boolean {
            return oldItem == newItem
        }
    }

    var differ = AsyncListDiffer(this, diffCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PointViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: PointRvItemBinding =
            PointRvItemBinding.inflate(layoutInflater, parent, false)
        return PointViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PointViewHolder, position: Int) {
        holder.binding(differ.currentList.get(position))
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}