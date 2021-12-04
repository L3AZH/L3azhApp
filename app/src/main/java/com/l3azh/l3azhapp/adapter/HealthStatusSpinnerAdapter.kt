package com.l3azh.l3azhapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.l3azh.l3azhapp.R
import com.l3azh.l3azhapp.databinding.HealStatusItemSpinnerBinding

class HealthStatusSpinnerAdapter(
    context: Context,
    var healStatusList: List<String> = listOf(
        context.getString(R.string.normal_heal_status_label),
        context.getString(R.string.bad_heal_status_label)
    )
): ArrayAdapter<String>(context,0, healStatusList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view:View
        val binding: HealStatusItemSpinnerBinding
        if(convertView == null){
            val layoutInflater = LayoutInflater.from(parent.context)
            binding = HealStatusItemSpinnerBinding.inflate(layoutInflater,null,false)
            view = binding.root
            view.tag = binding
        }
        else{
            view = convertView
            binding = view.tag as HealStatusItemSpinnerBinding
        }
        binding.textViewHealStatusLabel.text = healStatusList.elementAt(position)
        return view
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view:View
        val binding: HealStatusItemSpinnerBinding
        if(convertView == null){
            val layoutInflater = LayoutInflater.from(parent.context)
            binding = HealStatusItemSpinnerBinding.inflate(layoutInflater,null,false)
            view = binding.root
            view.tag = binding
        }
        else{
            view = convertView
            binding = view.tag as HealStatusItemSpinnerBinding
        }
        binding.textViewHealStatusLabel.text = healStatusList.elementAt(position)
        return view
    }
}