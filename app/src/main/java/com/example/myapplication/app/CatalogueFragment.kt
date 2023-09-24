package com.example.myapplication.app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.catalogue.HardDriveAdapter
import com.example.myapplication.databinding.FragmentCatalogueBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class CatalogueFragment : Fragment() {
    private var _binding: FragmentCatalogueBinding? = null
    private val binding: FragmentCatalogueBinding get() = _binding!!


    private val adapter = HardDriveAdapter()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentCatalogueBinding.inflate(inflater, container, false)


        lifecycleScope.launch (Dispatchers.IO){
            val modelList = App.hardDriveRepository.getAllModels()

            withContext(Dispatchers.Main){
                val navController = findNavController()
                binding.apply {
                    rcView.layoutManager = LinearLayoutManager(context)
                    adapter.hardDriveList = modelList
                    rcView.adapter = adapter

                    adapter.onItemClick = {
                        val bundle = Bundle()
                        bundle.putParcelable("model", it)
                        navController.navigate(R.id.action_catalogueFragment2_to_diskPageFragment2, bundle)
                    }
                }
            }
        }

        return binding.root
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}