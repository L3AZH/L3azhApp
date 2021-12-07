package com.l3azh.l3azhapp.adapter

import android.icu.text.SimpleDateFormat
import androidx.recyclerview.widget.RecyclerView
import com.l3azh.l3azhapp.api.res.GetExamDayItemResData
import com.l3azh.l3azhapp.databinding.ExamDayRvItemBinding

class ExamDayViewHolder(val binding:ExamDayRvItemBinding):RecyclerView.ViewHolder(binding.root) {

    fun binding(item:GetExamDayItemResData){
        binding.tvCodeSubject.text = item.codeSubject
        binding.tvNameSubject.text = item.nameSubject
        binding.tvExamDay.text = SimpleDateFormat("dd/MM/yyyy").format(item.examDay)
        binding.tvClassRoomExam.text = item.examRoom
        binding.tvSessionBegin.text = item.sessionBegin.toString()
        binding.tvTimeBegin.text = item.timeBegin
        binding.tvMinusNumber.text = item.minusNumber.toString()
    }
}