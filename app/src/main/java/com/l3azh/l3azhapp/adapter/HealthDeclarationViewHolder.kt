package com.l3azh.l3azhapp.adapter

import android.graphics.BitmapFactory
import androidx.recyclerview.widget.RecyclerView
import com.l3azh.l3azhapp.api.res.GetHealthDeclarationItemResData
import com.l3azh.l3azhapp.databinding.MedicalDeclarationRvItemBinding
import com.l3azh.l3azhapp.utils.Utils

class HealthDeclarationViewHolder(val binding: MedicalDeclarationRvItemBinding)
    :RecyclerView.ViewHolder(binding.root) {

        fun binding(
            item:GetHealthDeclarationItemResData,
            listener:HealthDeclarationAdapter.HealthDeclarationListener
        ){
            binding.tvTime.text = Utils.convertTimeStampToStringDate(item.time)
            binding.imageViewQrCode.setImageBitmap(
                BitmapFactory.decodeByteArray(
                    item.qrPic.data,
                    0,
                    item.qrPic.data.size
                )
            )
            itemView.setOnClickListener {
                listener.onItemClickListener(item)
            }
        }
}