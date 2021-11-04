package com.l3azh.l3azhapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.l3azh.l3azhapp.R
import com.l3azh.l3azhapp.databinding.RoleItemSpinnerBinding

class RoleSpinnerAdapter(
    context: Context,
    var roleList: List<String> = listOf(
        context.getString(R.string.student_role_label),
        context.getString(R.string.teacher_role_label)
    )
) : ArrayAdapter<String>(context, 0, roleList) {

    fun getCodeRoleForItemSelected(roleSelected: String): String {
        if (roleSelected.equals(context.getString(R.string.student_role_label))) {
            return context.getString(R.string.student_role_code_label)
        } else if (roleSelected.equals(context.getString(R.string.teacher_role_label))) {
            return context.getString(R.string.teacher_role_code_label)
        } else {
            return context.getString(R.string.error_role_code_label)
        }
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view:View
        val binding: RoleItemSpinnerBinding
        if(convertView == null){
            val layoutInflater = LayoutInflater.from(parent.context)
            binding = RoleItemSpinnerBinding.inflate(layoutInflater,null,false)
            view = binding.root
            view.tag = binding
        }
        else{
            view = convertView
            binding = view.tag as RoleItemSpinnerBinding
        }
        binding.textViewRoleLabel.text = roleList.elementAt(position)
        return view
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view:View
        val binding: RoleItemSpinnerBinding
        if(convertView == null){
            val layoutInflater = LayoutInflater.from(parent.context)
            binding = RoleItemSpinnerBinding.inflate(layoutInflater,null,false)
            view = binding.root
            view.tag = binding
        }
        else{
            view = convertView
            binding = view.tag as RoleItemSpinnerBinding
        }
        binding.textViewRoleLabel.text = roleList.elementAt(position)
        return view
    }
}