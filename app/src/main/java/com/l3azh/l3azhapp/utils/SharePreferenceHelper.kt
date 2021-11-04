package com.l3azh.l3azhapp.utils

import android.content.Context

class SharePreferenceHelper {
    companion object {
        private const val SHARE_PREFERENCES_NAME = "com.l3azh.l3azhapp.utils.l3azh_cache"
        private const val TOKEN_KEY = "token"
        private const val USERNAME_KEY = "username"
        private const val PASSWORD_KEY = "password"
        private const val CODE = "code"
        private const val ROLE = "role"

        fun getToken(context: Context): String? {
            val pref = context.getSharedPreferences(SHARE_PREFERENCES_NAME, Context.MODE_PRIVATE)
            val token = pref.getString(TOKEN_KEY, null)
            return token
        }

        fun saveToken(context: Context, token: String) {
            val pref = context.getSharedPreferences(SHARE_PREFERENCES_NAME, Context.MODE_PRIVATE)
            pref.edit().apply {
                putString(TOKEN_KEY, token)
            }.apply()
        }

        fun saveUsername(context: Context, username: String) {
            val pref = context.getSharedPreferences(SHARE_PREFERENCES_NAME, Context.MODE_PRIVATE)
            pref.edit().apply {
                putString(USERNAME_KEY, username)
            }.apply()
        }

        fun getUsername(context: Context): String? {
            val pref = context.getSharedPreferences(SHARE_PREFERENCES_NAME, Context.MODE_PRIVATE)
            val username = pref.getString(USERNAME_KEY, null)
            return username
        }

        fun savePassword(context: Context, password: String) {
            val pref = context.getSharedPreferences(SHARE_PREFERENCES_NAME, Context.MODE_PRIVATE)
            pref.edit().apply {
                putString(PASSWORD_KEY, password)
            }.apply()
        }

        fun getPassword(context: Context): String? {
            val pref = context.getSharedPreferences(SHARE_PREFERENCES_NAME, Context.MODE_PRIVATE)
            val password = pref.getString(PASSWORD_KEY, null)
            return password
        }

        fun saveCodeStudentOrTeacher(context: Context, codeStudentOrTeacher: String) {
            val pref = context.getSharedPreferences(SHARE_PREFERENCES_NAME, Context.MODE_PRIVATE)
            pref.edit().apply {
                putString(CODE, codeStudentOrTeacher)
            }.apply()
        }

        fun getCodeStudentOrTeacher(context: Context): String? {
            val pref = context.getSharedPreferences(SHARE_PREFERENCES_NAME, Context.MODE_PRIVATE)
            val code = pref.getString(CODE, null)
            return code
        }

        fun saveRole(context: Context, role: String) {
            val pref = context.getSharedPreferences(SHARE_PREFERENCES_NAME, Context.MODE_PRIVATE)
            pref.edit().apply {
                putString(ROLE, role)
            }.apply()
        }

        fun getRole(context: Context): String? {
            val pref = context.getSharedPreferences(SHARE_PREFERENCES_NAME, Context.MODE_PRIVATE)
            val role = pref.getString(ROLE, null)
            return role
        }
    }
}