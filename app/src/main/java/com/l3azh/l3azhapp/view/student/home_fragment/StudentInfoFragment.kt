package com.l3azh.l3azhapp.view.student.home_fragment

import android.annotation.SuppressLint
import android.content.res.Resources
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.ViewModelProvider
import com.l3azh.l3azhapp.R
import com.l3azh.l3azhapp.api.res.ErrorResData
import com.l3azh.l3azhapp.api.res.GetInfoStudentResData
import com.l3azh.l3azhapp.databinding.FragmentStudentInfoBinding
import com.l3azh.l3azhapp.dialog.LoadingDialog
import com.l3azh.l3azhapp.utils.SharePreferenceHelper
import com.l3azh.l3azhapp.utils.Utils
import com.l3azh.l3azhapp.viewmodel.student.StudentInfoViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class StudentInfoFragment : Fragment() {

    val TAG = StudentInfoFragment::class.java.simpleName
    lateinit var binding: FragmentStudentInfoBinding
    lateinit var viewModel: StudentInfoViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentStudentInfoBinding.inflate(layoutInflater, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(StudentInfoViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadDataInfoStudent()
    }

    @SuppressLint("SetTextI18n")
    fun loadDataInfoStudent() {
        CoroutineScope(Dispatchers.IO).launch {
            LoadingDialog.show(requireActivity())
            val res = viewModel.getDataInfoStudent(
                SharePreferenceHelper.getToken(requireContext())!!,
                SharePreferenceHelper.getUsername(requireContext())!!
            ).await()
            Log.e(TAG, "loadDataInfoStudent: ${LoadingDialog.instance == null}")
            LoadingDialog.close()
            if (res["flag"] as Boolean) {
                val infoStudent = (res["data"] as GetInfoStudentResData)
                CoroutineScope(Dispatchers.Main).launch {
                    binding.textViewCodeStudent.text =
                        "${getString(R.string.code_label_student_info_fragment)} ${infoStudent.code}"
                    binding.textViewStudentName.text =
                        "${getString(R.string.name_label_student_info_fragment)} ${infoStudent.name}"
                    if (infoStudent.avatar != null) {
                        binding.shapeImageViewAvatarOfStudent.setImageBitmap(
                            BitmapFactory.decodeByteArray(
                                infoStudent.avatar.data,
                                0,
                                infoStudent.avatar.data.size
                            )
                        )
                    } else {
                        binding.shapeImageViewAvatarOfStudent.setImageDrawable(
                            ResourcesCompat.getDrawable(
                                resources,
                                R.drawable.ic_baseline_person_64,
                                null
                            )
                        )
                    }
                }
            } else {
                Utils.showSnackbarError((res["data"] as ErrorResData).message, binding.root)
            }
        }
    }
}