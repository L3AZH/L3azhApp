package com.l3azh.l3azhapp.view.student.medical_declaration_fragment

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AutoCompleteTextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.l3azh.l3azhapp.R
import com.l3azh.l3azhapp.adapter.HealthStatusSpinnerAdapter
import com.l3azh.l3azhapp.databinding.FragmentCreateNewMedicalDeclarationBinding
import com.l3azh.l3azhapp.dialog.InformDialog
import com.l3azh.l3azhapp.dialog.InformType
import com.l3azh.l3azhapp.dialog.LoadingDialog
import com.l3azh.l3azhapp.utils.SharePreferenceHelper
import com.l3azh.l3azhapp.viewmodel.student.StudentMedicalDeclarationViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CreateNewMedicalDeclarationFragment : Fragment() {

    private val TAG: String = CreateNewMedicalDeclarationFragment::class.java.simpleName

    lateinit var binding: FragmentCreateNewMedicalDeclarationBinding
    lateinit var viewModel: StudentMedicalDeclarationViewModel
    lateinit var spinnerAdapter: HealthStatusSpinnerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            FragmentCreateNewMedicalDeclarationBinding.inflate(layoutInflater,container, false)
        viewModel = ViewModelProvider(requireActivity()).get(StudentMedicalDeclarationViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpSpinner()
        setOnclickSaveBtn()
    }

    fun setUpSpinner(){
        spinnerAdapter = HealthStatusSpinnerAdapter(requireContext())
        (binding.editTextHealthStatus as AutoCompleteTextView).setAdapter(spinnerAdapter)
        (binding.editTextHealthStatus as AutoCompleteTextView).setText(
            spinnerAdapter.getItem(0),false
        )
    }

    fun setOnclickSaveBtn(){
        binding.saveHealthDeclarationBtn.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                LoadingDialog.show(requireActivity())
                val result = viewModel.createNewHealthDeclaration(
                    SharePreferenceHelper.getToken(requireContext())!!,
                    binding.editTextHealthStatus.text.toString(),
                    binding.editTextStartDestination.text.toString(),
                    binding.editTextEndDestination.text.toString(),
                    SharePreferenceHelper.getUsername(requireContext())!!
                ).await()
                LoadingDialog.close()
                if(result["flag"] as Boolean){
                    val informSuccess = InformDialog(InformType.SUCCESS,"Lưu tờ khai thành công")
                    informSuccess.onCancel(object : DialogInterface{
                        override fun cancel() {
                            val goBackToListHealthDeclarationScreen =
                                CreateNewMedicalDeclarationFragmentDirections
                                    .actionCreateNewMedicalDeclarationFragmentToMedicalDeclarationFragment()
                            findNavController().navigate(goBackToListHealthDeclarationScreen)
                        }

                        override fun dismiss() {
                            val goBackToListHealthDeclarationScreen =
                                CreateNewMedicalDeclarationFragmentDirections
                                    .actionCreateNewMedicalDeclarationFragmentToMedicalDeclarationFragment()
                            findNavController().navigate(goBackToListHealthDeclarationScreen)
                        }
                    })
                    informSuccess.show(requireActivity().supportFragmentManager, TAG)
                }
                else{
                    val informError = InformDialog(InformType.FAIL, result["message"].toString())
                    informError.show(requireActivity().supportFragmentManager, TAG)
                }
            }
        }
    }
}