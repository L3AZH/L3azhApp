package com.l3azh.l3azhapp.adapter

import android.content.Context
import android.icu.text.SimpleDateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.l3azh.l3azhapp.api.res.GetSemesterSchoolYearWeekItemResData
import com.l3azh.l3azhapp.databinding.SemesterSchoolYearItemSpinnerBinding
import com.l3azh.l3azhapp.databinding.WeekNumberItemSpinnerBinding
import com.l3azh.l3azhapp.utils.Utils

class WeekNumberSpinnerAdapter(
    context: Context,
    var listWeekNumber:MutableList<GetSemesterSchoolYearWeekItemResData> = mutableListOf()
): ArrayAdapter<GetSemesterSchoolYearWeekItemResData>(context, 0, listWeekNumber) {

    fun getCodeValueSelectItem(itemSelected: String): String{
        Utils.logError(this, itemSelected)
        return itemSelected.substring(0,16)
    }

    fun setListData(listWeekNumberInput: List<GetSemesterSchoolYearWeekItemResData>){
        Utils.logError(this.javaClass,"Test")
        listWeekNumber.clear()
        listWeekNumber.addAll(listWeekNumberInput)
        Utils.logError(this.javaClass,listWeekNumber.size.toString())
        notifyDataSetChanged()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view:View
        val binding: WeekNumberItemSpinnerBinding
        if(convertView == null){
            val layoutInflater = LayoutInflater.from(parent.context)
            binding = WeekNumberItemSpinnerBinding.inflate(layoutInflater,null,false)
            view = binding.root
            view.tag = binding
        }
        else{
            view = convertView
            binding = view.tag as WeekNumberItemSpinnerBinding
        }
        val sdf = SimpleDateFormat("dd/MM/yyyy")
        val item = listWeekNumber.get(position)
        binding.textViewWeekNumber.text =
            "${item.semesterSchoolYearWeek} ( ${sdf.format(item.startDate)} - ${sdf.format(item.endDate)} )"
        return view
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view:View
        val binding: WeekNumberItemSpinnerBinding
        if(convertView == null){
            val layoutInflater = LayoutInflater.from(parent.context)
            binding = WeekNumberItemSpinnerBinding.inflate(layoutInflater,null,false)
            view = binding.root
            view.tag = binding
        }
        else{
            view = convertView
            binding = view.tag as WeekNumberItemSpinnerBinding
        }
        val sdf = SimpleDateFormat("dd/MM/yyyy")
        val item = listWeekNumber.get(position)
        binding.textViewWeekNumber.text =
            "${item.semesterSchoolYearWeek} ( ${sdf.format(item.startDate)} - ${sdf.format(item.endDate)}"
        return view
    }
}