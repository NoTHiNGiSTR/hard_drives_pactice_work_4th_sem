package com.example.myapplication.app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.FragmentDiskPageBinding
import com.example.myapplication.entities.HardDrive
import com.example.myapplication.entities.UserHardDrive
import com.example.myapplication.sharedpreferences.SharedPref
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class DiskPageFragment: Fragment() {

    val bundle = Bundle()
    private var _binding: FragmentDiskPageBinding? = null
    private val binding: FragmentDiskPageBinding get() = _binding!!




    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentDiskPageBinding.inflate(inflater, container, false)



        val user = SharedPref(requireContext())



        val item = arguments?.getParcelable<HardDrive>("model")
        binding.modelName.text = item!!.model_name
        binding.diskTable.developer.text = item!!.developer
        binding.diskTable.capacity.text = item!!.capacity
        binding.diskTable.dest.text = item!!.destination
        binding.diskTable.formFactor.text = item!!.form_factor
        binding.diskTable.interf.text = item!!.inter
        binding.diskTable.bufMem.text = item!!.buf_memory
        binding.diskTable.shpSpeed.text = item!!.shp_speed
        binding.extraInfo.text = item!!.extraInfo
        Glide.with(binding.mainImage.context).load(item.imageID).into(binding.mainImage)



        if (user.getPreferenceString("id") != null){
            lifecycleScope.launch(Dispatchers.IO) {
                if (App.userHardDriveRepository.isHardDriveFavouriteByUser(user.getPreferenceString("id")!!.toInt(), item!!.id!!) != null){
                    withContext(Dispatchers.Main){
                        binding.toFav.text = "Убрать из избранного"
                    }

                }
            }
        }


        binding.toFav.setOnClickListener(){
            if (SharedPref(requireContext()).getPreferenceString("login_status") != null) {
                val userId = SharedPref(requireContext()).getPreferenceString("id")!!.toInt()
                lifecycleScope.launch(Dispatchers.IO) {
                    if (App.userHardDriveRepository.isHardDriveFavouriteByUser(userId, item!!.id!!) != null){
                        App.userHardDriveRepository.delete(UserHardDrive(userId, item!!.id!!))
                        withContext(Dispatchers.Main){
                            binding.toFav.text = "В избранное"
                        }


                    }
                    else{
                        App.userHardDriveRepository.insert(UserHardDrive(userId, item.id!!))
                        withContext(Dispatchers.Main){
                            binding.toFav.text = "Убрать из избранного"
                        }


                    }
                }
            }
            else{
                Toast.makeText(context,"Пожалуйста выполните вход.", Toast.LENGTH_SHORT).show()
            }

        }

        binding.toComp.setOnClickListener(){
            if (SharedPref(requireContext()).getPreferenceString("login_status") != null) {
                if (item in App.compareStack){
                    Toast.makeText(context, "Элемент уже в сравнении.", Toast.LENGTH_SHORT).show()
                }
                else{
                    if(App.compareStack.size == 2){
                        App.compareStack.removeFirst()
                        App.compareStack.addLast(item)
                    }
                    else if (App.compareStack.size < 2){
                        App.compareStack.addLast(item)
                    }
                }
            }
            else{
                Toast.makeText(context, "Пожалуйста выполните вход", Toast.LENGTH_SHORT).show()
            }


        }

        return binding.root
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}