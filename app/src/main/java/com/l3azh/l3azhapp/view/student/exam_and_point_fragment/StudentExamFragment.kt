package com.l3azh.l3azhapp.view.student.exam_and_point_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AutoCompleteTextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.l3azh.l3azhapp.R
import com.l3azh.l3azhapp.adapter.ExamDayAdpater
import com.l3azh.l3azhapp.adapter.SemesterSchoolYearSpinnerAdapter
import com.l3azh.l3azhapp.adapter.SubjectInWeekAdapter
import com.l3azh.l3azhapp.adapter.WeekNumberSpinnerAdapter
import com.l3azh.l3azhapp.databinding.FragmentStudentExamBinding
import com.l3azh.l3azhapp.dialog.LoadingDialog
import com.l3azh.l3azhapp.utils.SharePreferenceHelper
import com.l3azh.l3azhapp.utils.Utils
import com.l3azh.l3azhapp.viewmodel.student.StudentExamDayViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class StudentExamFragment : Fragment() {

    private val TAG: String = StudentExamFragment::class.java.simpleName
    lateinit var binding: FragmentStudentExamBinding
    lateinit var viewModel: StudentExamDayViewModel
    lateinit var adapter: ExamDayAdpater
    lateinit var semesterSchoolYearSpinnerAdapter: SemesterSchoolYearSpinnerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentStudentExamBinding.inflate(layoutInflater, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(StudentExamDayViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpSpinner()
        setUpRv()
    }

    fun setUpSpinner() {
        semesterSchoolYearSpinnerAdapter = SemesterSchoolYearSpinnerAdapter(requireContext())
        (binding.editTextSemesterSchoolYear as AutoCompleteTextView)
            .setAdapter(semesterSchoolYearSpinnerAdapter)
        (binding.editTextSemesterSchoolYear as AutoCompleteTextView)
            .setText(semesterSchoolYearSpinnerAdapter.getItem(0), false)
        (binding.editTextSemesterSchoolYear as AutoCompleteTextView).onItemClickListener =
            object : AdapterView.OnItemClickListener {
                override fun onItemClick(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    CoroutineScope(Dispatchers.IO).launch {
                        val loadingDialog = LoadingDialog()
                        loadingDialog.show(requireActivity().supportFragmentManager, TAG)
                        val result = viewModel.getListExamDay(
                            SharePreferenceHelper.getToken(requireContext())!!,
                            semesterSchoolYearSpinnerAdapter.getCodeValueSelectItemForExamAndPoint(
                                binding.editTextSemesterSchoolYear.text.toString()
                            ),
                            SharePreferenceHelper.getUsername(requireContext())!!
                        ).await()
                        while (loadingDialog.dialog == null) {
                            delay(1000)
                        }
                        loadingDialog.cancelDialog()
                        if (!(result["flag"] as Boolean)) {
                            CoroutineScope(Dispatchers.Main).launch {
                                binding.imageViewEmptyPic.visibility = View.VISIBLE
                                binding.rvListExamDay.visibility = View.INVISIBLE
                            }
                            Utils.showSnackbarError(result["message"].toString(), binding.root)
                        }
                    }
                }
            }
    }

    fun setUpRv() {
        adapter = ExamDayAdpater()
        binding.rvListExamDay.layoutManager = LinearLayoutManager(requireContext())
        binding.rvListExamDay.adapter = adapter
        viewModel.listExamDay.observe(viewLifecycleOwner, {
            adapter.differ.submitList(it)
            if (it.isNullOrEmpty()) {
                binding.imageViewEmptyPic.visibility = View.VISIBLE
                binding.rvListExamDay.visibility = View.INVISIBLE
            } else {
                binding.imageViewEmptyPic.visibility = View.INVISIBLE
                binding.rvListExamDay.visibility = View.VISIBLE
            }
        })
        CoroutineScope(Dispatchers.IO).launch {
            val loadingDialog = LoadingDialog()
            loadingDialog.show(requireActivity().supportFragmentManager, TAG)
            val result = viewModel.getListExamDay(
                SharePreferenceHelper.getToken(requireContext())!!,
                semesterSchoolYearSpinnerAdapter.getCodeValueSelectItemForExamAndPoint(
                    binding.editTextSemesterSchoolYear.text.toString()
                ),
                SharePreferenceHelper.getUsername(requireContext())!!
            ).await()
            while (loadingDialog.dialog == null) {
                delay(1000)
            }
            loadingDialog.cancelDialog()
            if (!(result["flag"] as Boolean)) {
                Utils.showSnackbarError(result["message"].toString(), binding.root)
            }
        }
    }
}