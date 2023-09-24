package com.example.myapplication.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.catalogue.HardDriveAdapter
import com.example.myapplication.databinding.FragmentFavouritesBinding
import com.example.myapplication.sharedpreferences.SharedPref
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class FavouritesFragment : Fragment() {

    private var _binding: FragmentFavouritesBinding? = null
    private val binding: FragmentFavouritesBinding get() = _binding!!

    private val adapter = HardDriveAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentFavouritesBinding.inflate(inflater, container, false)
        val context = requireContext()
        val userID = SharedPref(context).getPreferenceString("id")!!.toInt()


        lifecycleScope.launch (Dispatchers.IO){
            val modelList = App.userHardDriveRepository.getFavouriteHardDrivesByUserId(userID)

            withContext(Dispatchers.Main){
                val navController = findNavController()
                binding.apply {
                    rcView.layoutManager = LinearLayoutManager(context)
                    adapter.hardDriveList = modelList
                    rcView.adapter = adapter

                    adapter.onItemClick = {
                        val bundle = Bundle()
                        bundle.putParcelable("model", it)
                        navController.navigate(com.example.myapplication.R.id.action_favouritesFragment_to_diskPageFragment2, bundle)
                    }
                }
            }
        }





        return binding.root
    }


}