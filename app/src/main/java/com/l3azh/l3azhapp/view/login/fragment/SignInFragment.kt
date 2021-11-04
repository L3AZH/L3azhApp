package com.l3azh.l3azhapp.view.login.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AutoCompleteTextView
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.l3azh.l3azhapp.R
import com.l3azh.l3azhapp.adapter.RoleSpinnerAdapter
import com.l3azh.l3azhapp.databinding.FragmentSignInBinding
import com.l3azh.l3azhapp.utils.Utils
import com.l3azh.l3azhapp.view.home_student.StudentHomeActivity
import com.l3azh.l3azhapp.view.home_teacher.TeacherHomeActivity
import com.l3azh.l3azhapp.viewmodel.login.SigninViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignInFragment : Fragment() {

    lateinit var binding: FragmentSignInBinding
    lateinit var roleAdapter: RoleSpinnerAdapter
    lateinit var viewModel: SigninViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSignInBinding.inflate(layoutInflater, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(SigninViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpdateRoleSpinner()
        setOnclickLoginBtn()
    }

    fun setUpdateRoleSpinner() {
        roleAdapter = RoleSpinnerAdapter(context = requireContext())
        (binding.editTextRole as AutoCompleteTextView).setAdapter(roleAdapter)
        (binding.editTextRole as AutoCompleteTextView).setText(
            roleAdapter.getItem(0)!!.toString(),
            false
        )
    }

    fun setOnclickLoginBtn() {
        binding.buttonSignIn.setOnClickListener {
            val roleCodeSelected =
                roleAdapter.getCodeRoleForItemSelected(binding.editTextRole.text.toString())
            CoroutineScope(Dispatchers.Default).launch {
                val result = viewModel.login(
                    requireContext(),
                    binding.editTextUsername.text.toString(),
                    binding.editTextPassword.text.toString(),
                    roleCodeSelected
                ).await()
                if (result["flag"] as Boolean) {
                    when (roleCodeSelected) {
                        getString(R.string.teacher_role_code_label) -> {
                            val goToTeacherHomeActivity =
                                Intent(requireContext(), TeacherHomeActivity::class.java)
                            startActivity(goToTeacherHomeActivity)
                        }
                        getString(R.string.student_role_code_label) -> {
                            val goToStudentHomeActivity =
                                Intent(requireContext(), StudentHomeActivity::class.java)
                            startActivity(goToStudentHomeActivity)
                        }
                    }
                } else {
                    Utils.showSnackbarError(result["message"].toString(), binding.root)
                }
            }
        }
        binding.textviewForgotPassword.setOnClickListener {
            val goToHomeStudent = Intent(activity, TeacherHomeActivity::class.java)
            startActivity(goToHomeStudent);
        }
    }
}