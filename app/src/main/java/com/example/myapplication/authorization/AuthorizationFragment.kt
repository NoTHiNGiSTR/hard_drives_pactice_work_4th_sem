package com.example.myapplication.authorization

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.PopupWindow
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.app.App
import com.example.myapplication.databinding.FragmentAuthorizationBinding
import com.example.myapplication.entities.User
import com.example.myapplication.sharedpreferences.SharedPref
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.junit.Assert
import org.junit.Test


class AuthorizationFragment : Fragment() {

    @Test
    fun correctLoginMustReturnTrue(){
        val login = "correctlogin"
        val result = isLoginFieldCorrect(login)
        Assert.assertTrue(result)
    }

    @Test
    fun incorrectMustReturnFalse(){
        val login = "incorrect login"
        val result = isLoginFieldCorrect(login)
        Assert.assertFalse(result)
    }

    @Test
    fun correctPasswordMustReturnTrue(){
        val password = "correctPassword"
        val result = isPasswordFieldCorrect(password)
        Assert.assertTrue(result)
    }

    @Test
    fun incorrectPasswordMustReturnFalse(){
        val password = "1234"
        val result = isPasswordFieldCorrect(password)
        Assert.assertFalse(result)
    }



    private var _binding: FragmentAuthorizationBinding? = null
    private val binding: FragmentAuthorizationBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentAuthorizationBinding.inflate(inflater, container, false)



        val context = requireContext()
        var sharedPreference = SharedPref(context)
        var edt_email = binding.edtEmail
        var edt_password = binding.edtPassword










        binding.btnLogin.setOnClickListener {
            val str_email_id = edt_email.text.toString()
            val str_password = edt_password.text.toString()

            if(str_email_id.equals("") || str_password.equals("")){
                Toast.makeText(context,"Please Enter Details.", Toast.LENGTH_SHORT).show()
            }else {
                lifecycleScope.launch(Dispatchers.IO){
                    val logUser = App.userRepository.checkUserExist(str_email_id, str_password)
                    val userExist: Boolean = (logUser != null)
                    if (userExist) {
                        withContext(Dispatchers.Main){
                            sharedPreference.save_String(logUser!!.id.toString(), logUser!!.name, logUser.email, "login_status","1")
                            sharedPreference.sharedUser = logUser


                            val control = findNavController()
                            val nav = arguments?.getString("nav")
                            if (nav == "toFavourites"){
                                control.navigate(R.id.action_authorizationFragment_to_favouritesFragment)
                            }
                            else if(nav == "toCompare"){
                                if (App.compareStack.size < 2){
                                    control.navigate(R.id.action_authorizationFragment_to_fragmentZatichka)
                                }
                                else{
                                    control.navigate(R.id.action_authorizationFragment_to_compareModelsFragment)
                                }

                            }
                            else if(nav == "toMain"){
                                control.navigate(R.id.action_authorizationFragment_to_mainFragment)
                            }
                        }

                    } else {
                        withContext(Dispatchers.Main){
                            Toast.makeText(context,"Enter Valid Email Id & Password.", Toast.LENGTH_SHORT).show()
                        }

                    }
                }

            }
        }


        binding.btnSignUp.setOnClickListener {
            fun_SignUp_PopupWindow()
        }



        return binding.root
    }

    private fun isLoginFieldCorrect(login: String): Boolean {
        return !(login.equals("") || (' ' in login))
    }

    private fun isPasswordFieldCorrect(password: String): Boolean {
        return !(password.equals("") || password.length < 8)
    }








    private fun fun_SignUp_PopupWindow() {

        val layoutInflater = requireContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val popView: View = layoutInflater.inflate(R.layout.sign_up_window, null)
        val popupWindow: PopupWindow
        popupWindow = PopupWindow(popView, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT,true)
        popupWindow.showAtLocation(popView, Gravity.CENTER, 0, 0)

        val btn_dia_submit = popView.findViewById(R.id.btn_dia_submit) as Button
        btn_dia_submit.setOnClickListener {
            val str_dia_email_id = popView.findViewById<EditText>(R.id.edt_dia_email_id).text.toString()
            val str_dia_password = popView.findViewById<EditText>(R.id.edt_dia_password).text.toString()
            val str_dia_name = popView.findViewById<EditText>(R.id.edt_dia_name).text.toString()

            if(str_dia_email_id.equals("") || str_dia_password.equals("")){
                Toast.makeText(requireContext(),"Please Enter Details.",Toast.LENGTH_SHORT).show()
            }else {
                popupWindow.dismiss()

                lifecycleScope.launch(Dispatchers.IO) {
                    App.userRepository.insert(User(null, str_dia_name, str_dia_email_id, str_dia_password))
                }
                Toast.makeText(requireContext(),"Your Account Created Successfully.",Toast.LENGTH_SHORT).show()
            }
        }
    }

}