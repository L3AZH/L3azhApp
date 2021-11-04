package com.l3azh.l3azhapp.view.login.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.l3azh.l3azhapp.R
import com.l3azh.l3azhapp.databinding.FragmentSplashBinding
import com.l3azh.l3azhapp.utils.SharePreferenceHelper
import com.l3azh.l3azhapp.utils.Utils
import com.l3azh.l3azhapp.view.home_student.StudentHomeActivity
import com.l3azh.l3azhapp.view.home_teacher.TeacherHomeActivity
import com.l3azh.l3azhapp.viewmodel.login.SplashViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment : Fragment() {

    lateinit var binding: FragmentSplashBinding
    lateinit var viewModel: SplashViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSplashBinding.inflate(layoutInflater, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(SplashViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        CoroutineScope(Dispatchers.Default).launch {
            delay(4000)
            CoroutineScope(Dispatchers.Main).launch {
                if (viewModel.checkExistToken(requireContext())) {
                    val role = SharePreferenceHelper.getRole(requireContext())
                    if (role == null) {
                        Utils.logError(this, getString(R.string.get_role_from_sharePref_null))
                        findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToSignInFragment())
                    } else {
                        when (role) {
                            getString(R.string.student_role_code_label) -> {
                                val goToStudentActivity =
                                    Intent(requireActivity(), StudentHomeActivity::class.java)
                                startActivity(goToStudentActivity)
                            }
                            getString(R.string.teacher_role_code_label) -> {
                                val goToTeacherActivity =
                                    Intent(requireActivity(), TeacherHomeActivity::class.java)
                                startActivity(goToTeacherActivity)
                            }
                            else -> {
                                Utils.logError(
                                    this,
                                    getString(R.string.invalid_role_from_sharePref)
                                )
                                findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToSignInFragment())
                            }
                        }
                    }
                } else {
                    Utils.logDebug(this, getString(R.string.empty_token_from_sharePref))
                    findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToSignInFragment())
                }
            }
        }
    }
}