package com.example.myapplication.app


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentMainBinding
import com.example.myapplication.sharedpreferences.SharedPref
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.junit.Test


class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding get() = _binding!!




    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val bundle = Bundle()
        val sharedPreference = SharedPref(requireContext())

        if (sharedPreference.getPreferenceString("username") != null){
            binding.welcomeTitle.text = "Добро пожаловать " + sharedPreference.getPreferenceString("username")
        }


        binding.auth.setOnClickListener{
            if (sharedPreference.getPreferenceString("login_status") == null){
                bundle.putString("nav", "toMain")
                Navigation.findNavController(it).navigate(R.id.action_mainFragment_to_authorizationFragment, bundle)
            }
            else{
                Navigation.findNavController(it).navigate(R.id.action_mainFragment_to_logOutFragment)
            }
        }

        binding.catalogue.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.action_mainFragment_to_catalogueFragment2)
        }

        binding.newsModels.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.action_mainFragment_to_newModels)
        }


        binding.favourites.setOnClickListener{
            if (sharedPreference.getPreferenceString("login_status")!=null){
                Navigation.findNavController(it).navigate(R.id.action_mainFragment_to_favouritesFragment)
            }
            else{
                lifecycleScope.launch(Dispatchers.IO) {
                    val list = App.hardDriveRepository.getAllModels()
                    if (list == null){
                        withContext(Dispatchers.Main){
                            Navigation.findNavController(it).navigate(R.id.action_mainFragment_to_fragmentZatichka)
                        }
                    }
                    else{
                        withContext(Dispatchers.Main) {
                            bundle.putString("nav", "toFavourites")
                            Navigation.findNavController(it).navigate(R.id.action_mainFragment_to_authorizationFragment, bundle)
                        }
                    }
                }

            }
        }

        binding.compare.setOnClickListener{
            if (sharedPreference.getPreferenceString("login_status")!=null){
                if (App.compareStack.size < 2){
                    Navigation.findNavController(it).navigate(R.id.action_mainFragment_to_fragmentZatichka)
                }
                else{
                    Navigation.findNavController(it).navigate(R.id.action_mainFragment_to_compareModelsFragment)
                }

            }
            else{
                bundle.putString("nav", "toCompare")
                Navigation.findNavController(it).navigate(R.id.action_mainFragment_to_authorizationFragment, bundle)

            }
        }
        return binding.root
    }



    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}