package com.l3azh.l3azhapp.di

import com.l3azh.l3azhapp.viewmodel.login.SigninViewModel
import com.l3azh.l3azhapp.viewmodel.login.SplashViewModel
import com.l3azh.l3azhapp.viewmodel.student.StudentInfoViewModel
import com.l3azh.l3azhapp.viewmodel.teacher.TeacherInfoViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RetrofitModule::class])
interface AppComponent {
    fun inject(splashViewModel: SplashViewModel)
    fun inject(signinViewModel: SigninViewModel)
    fun inject(studentInfoViewModel: StudentInfoViewModel)
    fun inject(teacherInfoViewModel: TeacherInfoViewModel)
}