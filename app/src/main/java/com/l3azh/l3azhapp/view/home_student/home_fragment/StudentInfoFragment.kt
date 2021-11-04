package com.l3azh.l3azhapp.view.home_student.home_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.l3azh.l3azhapp.R
import com.l3azh.l3azhapp.databinding.FragmentStudentInfoBinding

class StudentInfoFragment : Fragment() {

    lateinit var binding:FragmentStudentInfoBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentStudentInfoBinding.inflate(layoutInflater,container,false)
        return binding.root
    }



}