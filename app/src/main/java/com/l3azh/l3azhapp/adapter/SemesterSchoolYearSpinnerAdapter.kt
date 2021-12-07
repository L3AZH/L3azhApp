package com.l3azh.l3azhapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.l3azh.l3azhapp.databinding.SemesterSchoolYearItemSpinnerBinding

class SemesterSchoolYearSpinnerAdapter(
    context: Context,
    var listSemesterSchoolYear: List<String> = listOf(
        "HK2 Năm học 2020-2021",
        "HK3 Năm học 2020-2021",
        "HK1 Năm học 2021-2022"
    )
): ArrayAdapter<String>(context, 0, listSemesterSchoolYear){

    fun getCodeValueSelectItem(itemSelected: String): String{
        when(itemSelected){
            "HK2 Năm học 2020-2021" -> return "HK2-2020-2021"
            "HK3 Năm học 2020-2021" -> return "HK3-2020-2021"
            "HK1 Năm học 2021-2022" -> return "HK1-2021-2022"
            else -> return "null"
        }
    }

    fun getCodeValueSelectItemForExamAndPoint(itemSelected: String): String{
        when(itemSelected){
            "HK2 Năm học 2020-2021" -> return "220202021"
            "HK3 Năm học 2020-2021" -> return "320202021"
            "HK1 Năm học 2021-2022" -> return "120212022"
            else -> return "null"
        }
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view:View
        val binding: SemesterSchoolYearItemSpinnerBinding
        if(convertView == null){
            val layoutInflater = LayoutInflater.from(parent.context)
            binding = SemesterSchoolYearItemSpinnerBinding.inflate(layoutInflater,null,false)
            view = binding.root
            view.tag = binding
        }
        else{
            view = convertView
            binding = view.tag as SemesterSchoolYearItemSpinnerBinding
        }
        binding.textViewSemesterSchoolYear.text = listSemesterSchoolYear.elementAt(position)
        return view
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view:View
        val binding: SemesterSchoolYearItemSpinnerBinding
        if(convertView == null){
            val layoutInflater = LayoutInflater.from(parent.context)
            binding = SemesterSchoolYearItemSpinnerBinding.inflate(layoutInflater,null,false)
            view = binding.root
            view.tag = binding
        }
        else{
            view = convertView
            binding = view.tag as SemesterSchoolYearItemSpinnerBinding
        }
        binding.textViewSemesterSchoolYear.text = listSemesterSchoolYear.elementAt(position)
        return view
    }
}