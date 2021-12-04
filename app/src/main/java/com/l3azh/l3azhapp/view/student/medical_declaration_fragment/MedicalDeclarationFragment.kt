package com.l3azh.l3azhapp.view.student.medical_declaration_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.l3azh.l3azhapp.R
import com.l3azh.l3azhapp.adapter.HealthDeclarationAdapter
import com.l3azh.l3azhapp.api.res.GetHealthDeclarationItemResData
import com.l3azh.l3azhapp.databinding.FragmentMedicalDeclarationBinding
import com.l3azh.l3azhapp.dialog.LoadingDialog
import com.l3azh.l3azhapp.utils.SharePreferenceHelper
import com.l3azh.l3azhapp.utils.Utils
import com.l3azh.l3azhapp.viewmodel.student.StudentMedicalDeclarationViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MedicalDeclarationFragment : Fragment() {

    lateinit var binding:FragmentMedicalDeclarationBinding
    lateinit var viewModel: StudentMedicalDeclarationViewModel
    lateinit var adapter: HealthDeclarationAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMedicalDeclarationBinding.inflate(layoutInflater,container, false)
        viewModel = ViewModelProvider(requireActivity()).get(StudentMedicalDeclarationViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnclickCreateNewHealDeclarationFloatingBtn()
        setUpRv()
        setUpDataRv()
    }

    fun setUpRv(){
        adapter = HealthDeclarationAdapter(object : HealthDeclarationAdapter.HealthDeclarationListener{
            override fun onItemClickListener(item: GetHealthDeclarationItemResData) {
                return
            }
        })
        binding.rvListMedicalDeclaration.layoutManager = LinearLayoutManager(requireContext())
        binding.rvListMedicalDeclaration.adapter = adapter
    }

    fun setUpDataRv(){
        viewModel.listHealthDeclaration.observe(viewLifecycleOwner,{
            adapter.differ.submitList(it)
            if(it.isNullOrEmpty()){
                binding.imageViewEmptyPic.visibility = View.VISIBLE
                binding.rvListMedicalDeclaration.visibility = View.INVISIBLE
            }
            else{
                binding.imageViewEmptyPic.visibility = View.INVISIBLE
                binding.rvListMedicalDeclaration.visibility = View.VISIBLE
            }
        })
        CoroutineScope(Dispatchers.IO).launch {
            LoadingDialog.show(requireActivity())
            val result = viewModel.getListHealthDeclaration(
                SharePreferenceHelper.getToken(requireContext())!!,
                SharePreferenceHelper.getUsername(requireContext())!!
            ).await()
            LoadingDialog.close()
            if(!(result["flag"] as Boolean)){
                Utils.showSnackbarError(result["message"].toString(),binding.root)
            }
        }
    }

    fun setOnclickCreateNewHealDeclarationFloatingBtn(){
        binding.floatingBtnCreateNewMedicalDeclaration.setOnClickListener {
            val goToCreateNewDeclarationScreen =
                MedicalDeclarationFragmentDirections
                    .actionMedicalDeclarationFragmentToCreateNewMedicalDeclarationFragment()
            findNavController().navigate(goToCreateNewDeclarationScreen)
        }
    }
}