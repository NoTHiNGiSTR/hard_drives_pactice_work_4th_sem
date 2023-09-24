package com.example.myapplication.sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import com.example.myapplication.entities.User
import org.junit.Test

class SharedPref (context: Context) {
    private val preference_Name = "Nothing_is_true"
    val shared_Pref: SharedPreferences = context.getSharedPreferences(preference_Name, Context.MODE_PRIVATE)
    var sharedUser: User? = null



    fun save_String(id: String, username: String, email: String, key_name: String, text: String) {
        val editor: SharedPreferences.Editor = shared_Pref.edit()
        editor.putString(key_name, text)
        editor.putBoolean(IS_LOGIN, true)
        editor.putString(KEY_USERNAME, username)
        editor.putString(KEY_EMAIL, email)
        editor.putString(KEY_USER_ID, id)
        editor.commit()
    }

    companion object{
        val MODEL1 = "model1"
        val MODEL2 = "model2"
        val IS_LOGIN = "isLoggedIn"
        val KEY_USERNAME = "username"
        val KEY_EMAIL = "email"
        val KEY_USER_ID = "id"
    }

    fun getPreferenceString(key_name: String): String? {
        return shared_Pref.getString(key_name, null)
    }


    fun clearSharedPreference() {
        val editor: SharedPreferences.Editor = shared_Pref.edit()
        editor.clear()
        editor.commit()
    }
}