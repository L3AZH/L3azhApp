package com.l3azh.l3azhapp.adapter

import androidx.recyclerview.widget.RecyclerView
import com.l3azh.l3azhapp.api.res.GetLichHocItemResData
import com.l3azh.l3azhapp.databinding.SubjectInWeekRvItemBinding

class SubjectInWeekViewHolder(val binding: SubjectInWeekRvItemBinding):RecyclerView.ViewHolder(binding.root) {
    fun binding(item: GetLichHocItemResData){
        binding.tvCodeSubject.text = item.codeSubject
        binding.tvClassRoom.text = item.classRoom
        binding.tvDayNumberInWeek.text = item.dayNumber.toString()
        binding.tvTimeBegin.text = item.timeBegin.toString()
        binding.tvSessionBegin.text = item.sessionBegin.toString()
    }
}