package com.l3azh.l3azhapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.l3azh.l3azhapp.api.res.GetHealthDeclarationItemResData
import com.l3azh.l3azhapp.databinding.MedicalDeclarationRvItemBinding

class HealthDeclarationAdapter(
    var listener:HealthDeclarationListener
)
    :RecyclerView.Adapter<HealthDeclarationViewHolder>() {



    var differCallBack = object : DiffUtil.ItemCallback<GetHealthDeclarationItemResData>(){
        override fun areItemsTheSame(
            oldItem: GetHealthDeclarationItemResData,
            newItem: GetHealthDeclarationItemResData
        ): Boolean {
            return oldItem.codeDeclaration == newItem.codeDeclaration
        }
        override fun areContentsTheSame(
            oldItem: GetHealthDeclarationItemResData,
            newItem: GetHealthDeclarationItemResData
        ): Boolean {
            return  oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HealthDeclarationViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: MedicalDeclarationRvItemBinding =
            MedicalDeclarationRvItemBinding.inflate(layoutInflater,parent,false)
        return HealthDeclarationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HealthDeclarationViewHolder, position: Int) {
        holder.binding(differ.currentList.get(position), listener)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    interface HealthDeclarationListener{
        fun onItemClickListener(item:GetHealthDeclarationItemResData)
    }
}