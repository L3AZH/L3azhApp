package com.l3azh.l3azhapp.view.student.home_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.l3azh.l3azhapp.R
import com.l3azh.l3azhapp.databinding.FragmentStudentHomeBinding

class StudentHomeFragment : Fragment() {

    lateinit var binding: FragmentStudentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentStudentHomeBinding.inflate(layoutInflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClickImageView()
    }

    fun setOnClickImageView(){
        binding.imageViewHealDeclaration.setOnClickListener {
            val goToHealDeclarationScreen =
                StudentHomeFragmentDirections.actionStudentHomeFragmentToMedicalDeclarationFragment()
            findNavController().navigate(goToHealDeclarationScreen)
        }
        binding.imageViewCtdt.setOnClickListener {
            val goToCTDTScreen =
                StudentHomeFragmentDirections.actionStudentHomeFragmentToCtdtFragment()
            findNavController().navigate(goToCTDTScreen)
        }
        binding.imageViewTimeTable.setOnClickListener {
            val goToTimeTableScreen =
                StudentHomeFragmentDirections.actionStudentHomeFragmentToStudentTimeTableFragment()
            findNavController().navigate(goToTimeTableScreen)
        }
        binding.imageViewExamDay.setOnClickListener {
            val goToExamDayScreen =
                StudentHomeFragmentDirections.actionStudentHomeFragmentToStudentExamFragment()
            findNavController().navigate(goToExamDayScreen)
        }
        binding.imageViewPoint.setOnClickListener {
            val goToPointScreen =
                StudentHomeFragmentDirections.actionStudentHomeFragmentToStudentPointFragment()
            findNavController().navigate(goToPointScreen)
        }
    }
}