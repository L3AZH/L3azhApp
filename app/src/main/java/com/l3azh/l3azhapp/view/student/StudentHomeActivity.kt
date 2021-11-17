package com.l3azh.l3azhapp.view.student

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.l3azh.l3azhapp.R
import com.l3azh.l3azhapp.databinding.ActivityStudentHomeBinding

class StudentHomeActivity : AppCompatActivity() {

    lateinit var binding:ActivityStudentHomeBinding
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpNavigationDrawerAndToolBar()
    }

    fun setUpNavigationDrawerAndToolBar(){
        setSupportActionBar(binding.materialToolBarActivityStudentHome)
        val navHostFragment =
            supportFragmentManager
                .findFragmentById(R.id.fragment_container_view_activity_student_home) as NavHostFragment
        navController = navHostFragment.navController
        val appBarConfiguration =
            AppBarConfiguration(navController.graph,binding.drawerLayoutStudentActivity)
        binding.navViewActivityStudentHome.setupWithNavController(navController)
        binding.materialToolBarActivityStudentHome.setupWithNavController(navController,appBarConfiguration)
    }
}