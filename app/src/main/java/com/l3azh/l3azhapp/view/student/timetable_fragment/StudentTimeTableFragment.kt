package com.l3azh.l3azhapp.view.student.timetable_fragment

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
import com.l3azh.l3azhapp.adapter.SemesterSchoolYearSpinnerAdapter
import com.l3azh.l3azhapp.adapter.SubjectInWeekAdapter
import com.l3azh.l3azhapp.adapter.WeekNumberSpinnerAdapter
import com.l3azh.l3azhapp.databinding.FragmentStudentTimeTableBinding
import com.l3azh.l3azhapp.dialog.LoadingDialog
import com.l3azh.l3azhapp.utils.SharePreferenceHelper
import com.l3azh.l3azhapp.utils.Utils
import com.l3azh.l3azhapp.viewmodel.student.StudentTimeTableViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class StudentTimeTableFragment : Fragment() {

    private val TAG: String = StudentTimeTableFragment::class.java.simpleName
    lateinit var binding: FragmentStudentTimeTableBinding
    lateinit var viewModel: StudentTimeTableViewModel
    lateinit var semesterSchoolYearSpinnerAdapter: SemesterSchoolYearSpinnerAdapter
    lateinit var weekNumberSpinnerAdapter: WeekNumberSpinnerAdapter
    lateinit var subjectInWeekAdapter: SubjectInWeekAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentStudentTimeTableBinding.inflate(layoutInflater, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(StudentTimeTableViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpSpinner()
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
                        val result = viewModel.getListWeekNumber(
                            SharePreferenceHelper.getToken(requireContext())!!,
                            semesterSchoolYearSpinnerAdapter.getCodeValueSelectItem(
                                binding.editTextSemesterSchoolYear.text.toString()
                            )
                        ).await()
                        while (loadingDialog.dialog == null) {
                            delay(1000)
                        }
                        loadingDialog.cancelDialog()
                        if (!(result["flag"] as Boolean)) {
                            weekNumberSpinnerAdapter.setListData(listOf())
                            CoroutineScope(Dispatchers.Main).launch {
                                (binding.editTextWeekNumber as AutoCompleteTextView).setText(
                                    "",
                                    false
                                )
                                binding.imageViewEmptyPic.visibility = View.VISIBLE
                                binding.rvListSubjectInWeek.visibility = View.INVISIBLE
                            }
                            Utils.showSnackbarError(result["message"].toString(), binding.root)
                        }
                    }
                }
            }

        weekNumberSpinnerAdapter = WeekNumberSpinnerAdapter(requireContext())
        (binding.editTextWeekNumber as AutoCompleteTextView)
            .setAdapter(weekNumberSpinnerAdapter)
        viewModel.listWeekNumber.observe(viewLifecycleOwner, {
            if (!it.isNullOrEmpty()) {
                weekNumberSpinnerAdapter.setListData(it)
                (binding.editTextWeekNumber as AutoCompleteTextView).setText(
                    weekNumberSpinnerAdapter.getItem(0).toString(), false
                )
                if (viewModel.isFisrtTimeCreate) {
                    viewModel.setFlagAfterInitFirstTimeData()
                    CoroutineScope(Dispatchers.IO).launch {
                        val loadingDialog = LoadingDialog()
                        loadingDialog.show(requireActivity().supportFragmentManager, TAG)
                        val result = viewModel.getListSubjectInWeek(
                            SharePreferenceHelper.getToken(requireContext())!!,
                            weekNumberSpinnerAdapter.getCodeValueSelectItem(binding.editTextWeekNumber.text.toString()),
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
        })
        (binding.editTextWeekNumber as AutoCompleteTextView).onItemClickListener =
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
                        val result = viewModel.getListSubjectInWeek(
                            SharePreferenceHelper.getToken(requireContext())!!,
                            weekNumberSpinnerAdapter.getCodeValueSelectItem(binding.editTextWeekNumber.text.toString()),
                            SharePreferenceHelper.getUsername(requireContext())!!
                        ).await()
                        while (loadingDialog.dialog == null) {
                            delay(1000)
                        }
                        loadingDialog.cancelDialog()
                        if (!(result["flag"] as Boolean)) {
                            CoroutineScope(Dispatchers.Main).launch {
                                binding.imageViewEmptyPic.visibility = View.VISIBLE
                                binding.rvListSubjectInWeek.visibility = View.INVISIBLE
                            }
                            Utils.showSnackbarError(result["message"].toString(), binding.root)
                        }
                    }
                }
            }

        setUpRv()

        CoroutineScope(Dispatchers.IO).launch {
            val loadingDialog = LoadingDialog()
            loadingDialog.show(requireActivity().supportFragmentManager, TAG)
            val result = viewModel.getListWeekNumber(
                SharePreferenceHelper.getToken(requireContext())!!,
                semesterSchoolYearSpinnerAdapter.getCodeValueSelectItem(
                    binding.editTextSemesterSchoolYear.text.toString()
                )
            ).await()
            while (loadingDialog.dialog == null) {
                delay(1000)
            }
            loadingDialog.cancelDialog()
            if (!(result["flag"] as Boolean)) {
                Utils.showSnackbarError(result["message"].toString(), binding.root)
                CoroutineScope(Dispatchers.Main).launch {
                    binding.imageViewEmptyPic.visibility = View.VISIBLE
                    binding.rvListSubjectInWeek.visibility = View.INVISIBLE
                }
            }
        }
    }

    fun setUpRv() {
        subjectInWeekAdapter = SubjectInWeekAdapter()
        binding.rvListSubjectInWeek.layoutManager = LinearLayoutManager(requireContext())
        binding.rvListSubjectInWeek.adapter = subjectInWeekAdapter
        viewModel.listSubjectInWeek.observe(viewLifecycleOwner, {
            subjectInWeekAdapter.differ.submitList(it)
            if (it.isNullOrEmpty()) {
                binding.imageViewEmptyPic.visibility = View.VISIBLE
                binding.rvListSubjectInWeek.visibility = View.INVISIBLE
            } else {
                binding.imageViewEmptyPic.visibility = View.INVISIBLE
                binding.rvListSubjectInWeek.visibility = View.VISIBLE
            }
        })
    }
}