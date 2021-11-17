package com.l3azh.l3azhapp.view.teacher

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.l3azh.l3azhapp.R
import com.l3azh.l3azhapp.databinding.ActivityTeacherHomeBinding

class TeacherHomeActivity : AppCompatActivity() {

    lateinit var binding:ActivityTeacherHomeBinding
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTeacherHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpNavigationDrawerAndToolBar()
    }

    fun setUpNavigationDrawerAndToolBar(){
        setSupportActionBar(binding.materialToolBarActivityTeacherHome)
        val navHostFragment =
            supportFragmentManager
                .findFragmentById(R.id.fragment_container_view_activity_teacher_home) as NavHostFragment
        navController = navHostFragment.navController
        val appBarConfiguration =
            AppBarConfiguration(navController.graph,binding.drawerLayoutTeacherActivity)
        binding.navViewActivityTeacherHome.setupWithNavController(navController)
        binding.materialToolBarActivityTeacherHome.setupWithNavController(navController,appBarConfiguration)
    }
}