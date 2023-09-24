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
import com.example.myapplication.databinding.FragmentNewModelsBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class NewModelsFragment : Fragment() {

    private var _binding: FragmentNewModelsBinding? = null
    private val binding: FragmentNewModelsBinding get() = _binding!!

    private val adapter = HardDriveAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentNewModelsBinding.inflate(inflater, container, false)

        lifecycleScope.launch (Dispatchers.IO){
            val modList = App.hardDriveRepository.getAllModels()
            val a = modList.size - 5
            val b = modList.size - 1
            val modelList = modList.slice(a..b)

            withContext(Dispatchers.Main){
                val navController = findNavController()
                binding.apply {
                    rcViewNews.layoutManager = LinearLayoutManager(context)
                    adapter.hardDriveList = modelList
                    rcViewNews.adapter = adapter

                    adapter.onItemClick = {
                        val bundle = Bundle()
                        bundle.putParcelable("model", it)
                        navController.navigate(R.id.action_newModels_to_diskPageFragment2, bundle)
                    }
                }
            }


        }

        return binding.root
    }


}