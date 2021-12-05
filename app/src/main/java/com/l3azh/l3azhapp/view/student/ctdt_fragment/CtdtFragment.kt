package com.l3azh.l3azhapp.view.student.ctdt_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.l3azh.l3azhapp.R
import com.l3azh.l3azhapp.adapter.CTDTAdapter
import com.l3azh.l3azhapp.api.res.GetCTDTItemResData
import com.l3azh.l3azhapp.databinding.FragmentCtdtBinding
import com.l3azh.l3azhapp.dialog.LoadingDialog
import com.l3azh.l3azhapp.utils.SharePreferenceHelper
import com.l3azh.l3azhapp.utils.Utils
import com.l3azh.l3azhapp.viewmodel.student.StudentCTDTViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class CtdtFragment : Fragment() {

    lateinit var binding: FragmentCtdtBinding
    lateinit var viewModel: StudentCTDTViewModel
    lateinit var adapter: CTDTAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCtdtBinding.inflate(layoutInflater,container, false)
        viewModel = ViewModelProvider(requireActivity()).get(StudentCTDTViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRv()
        setUpDataRv()
    }

    fun setUpRv(){
        adapter = CTDTAdapter(object : CTDTAdapter.CTDTListner{
            override fun onItemClickListener(item: GetCTDTItemResData) {
                return
            }
        })
        binding.rvListCtdt.layoutManager = LinearLayoutManager(requireContext())
        binding.rvListCtdt.adapter = adapter
    }

    fun setUpDataRv(){
        viewModel.listCTDT.observe(viewLifecycleOwner,{
            adapter.differ.submitList(it)
            if(it.isNullOrEmpty()){
                binding.rvListCtdt.visibility = View.INVISIBLE
                binding.imageViewEmptyPic.visibility = View.VISIBLE
            }
            else{
                binding.rvListCtdt.visibility = View.VISIBLE
                binding.imageViewEmptyPic.visibility = View.INVISIBLE
            }
        })
        CoroutineScope(Dispatchers.IO).launch {
            LoadingDialog.show(requireActivity())
            val result = viewModel.getListCTDT(
                SharePreferenceHelper.getToken(requireContext())!!,
                SharePreferenceHelper.getUsername(requireContext())!!
            ).await()
            LoadingDialog.close()
            if(!(result["flag"] as Boolean)){
                Utils.showSnackbarError(result["message"].toString(),binding.root)
            }
        }
    }
}