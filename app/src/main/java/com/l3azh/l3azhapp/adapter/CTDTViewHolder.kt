package com.l3azh.l3azhapp.adapter

import androidx.recyclerview.widget.RecyclerView
import com.l3azh.l3azhapp.api.res.GetCTDTItemResData
import com.l3azh.l3azhapp.databinding.CtdtRvItemBinding

class CTDTViewHolder(val binding:CtdtRvItemBinding):RecyclerView.ViewHolder(binding.root) {
    fun binding(item:GetCTDTItemResData, listener:CTDTAdapter.CTDTListner){
        binding.tvCodeSubject.text = item.codeSubject
        binding.tvNameSubject.text = item.nameSubject
        binding.tvNumberOfCreditsSubject.text = item.numberOfCredits.toString()
        itemView.setOnClickListener {
            listener.onItemClickListener(item)
        }
    }
}