package com.l3azh.l3azhapp.adapter

import androidx.recyclerview.widget.RecyclerView
import com.l3azh.l3azhapp.api.res.GetPointItemResData
import com.l3azh.l3azhapp.databinding.PointRvItemBinding

class PointViewHolder(val binding: PointRvItemBinding):RecyclerView.ViewHolder(binding.root) {

    fun binding(item:GetPointItemResData){
        binding.tvCodeSubject.text = item.codeSubject
        binding.tvNameSubject.text = item.nameSubject

        binding.tvPointSysCc.text = item.pointSystemCC.toString()
        binding.tvPointSysSe.text = item.pointSystemSE.toString()
        binding.tvPointSysKt.text = item.pointSystemKT.toString()
        binding.tvPointSysTh.text = item.pointSystemTH.toString()
        binding.tvPointSysThi.text = item.pointSystemTHI.toString()

        binding.tvPointCc.text = item.pointCC.toString()
        binding.tvPointSe.text = item.pointSE.toString()
        binding.tvPointKt.text = item.pointKT.toString()
        binding.tvPointTh.text = item.pointTH.toString()
        binding.tvPointThi.text = item.pointTHI.toString()
    }
}