package com.example.myapplication.app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentLogOutBinding
import com.example.myapplication.sharedpreferences.SharedPref


class LogOutFragment : Fragment() {


    private var _binding: FragmentLogOutBinding? = null
    private val binding: FragmentLogOutBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentLogOutBinding.inflate(inflater, container, false)

        binding.logout.setOnClickListener{
            var sharedPreference = SharedPref(requireContext())
            sharedPreference!!.clearSharedPreference()
            Toast.makeText(requireContext(),"User LogOut Successfully.",Toast.LENGTH_SHORT).show()
            Navigation.findNavController(it).navigate(R.id.action_logOutFragment_to_mainFragment)
        }

        return binding.root
    }


}