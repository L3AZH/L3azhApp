package com.l3azh.l3azhapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.l3azh.l3azhapp.api.res.GetLichHocItemResData
import com.l3azh.l3azhapp.databinding.SubjectInWeekRvItemBinding

class SubjectInWeekAdapter():RecyclerView.Adapter<SubjectInWeekViewHolder>() {

    var differCallBack = object : DiffUtil.ItemCallback<GetLichHocItemResData>(){
        override fun areItemsTheSame(
            oldItem: GetLichHocItemResData,
            newItem: GetLichHocItemResData
        ): Boolean {
            return false
        }

        override fun areContentsTheSame(
            oldItem: GetLichHocItemResData,
            newItem: GetLichHocItemResData
        ): Boolean {
            return oldItem == newItem
        }
    }

    var differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectInWeekViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: SubjectInWeekRvItemBinding =
            SubjectInWeekRvItemBinding.inflate(layoutInflater,parent,false)
        return SubjectInWeekViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SubjectInWeekViewHolder, position: Int) {
        holder.binding(differ.currentList.get(position))
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}