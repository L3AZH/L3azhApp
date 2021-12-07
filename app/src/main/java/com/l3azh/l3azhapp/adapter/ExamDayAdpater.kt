package com.l3azh.l3azhapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.l3azh.l3azhapp.api.res.GetExamDayItemResData
import com.l3azh.l3azhapp.databinding.ExamDayRvItemBinding

class ExamDayAdpater:RecyclerView.Adapter<ExamDayViewHolder>() {

    var diffCallBack = object : DiffUtil.ItemCallback<GetExamDayItemResData>(){
        override fun areItemsTheSame(
            oldItem: GetExamDayItemResData,
            newItem: GetExamDayItemResData
        ): Boolean {
            return false
        }

        override fun areContentsTheSame(
            oldItem: GetExamDayItemResData,
            newItem: GetExamDayItemResData
        ): Boolean {
            return oldItem == newItem
        }
    }

    var differ = AsyncListDiffer(this, diffCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExamDayViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ExamDayRvItemBinding =
            ExamDayRvItemBinding.inflate(layoutInflater, parent, false)
        return ExamDayViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ExamDayViewHolder, position: Int) {
        holder.binding(differ.currentList.get(position))
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}