package com.example.myapplication.app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.FragmentCompareModelsBinding
import com.example.myapplication.entities.UserHardDrive
import com.example.myapplication.sharedpreferences.SharedPref
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.junit.Assert
import org.junit.Test


class CompareModelsFragment : Fragment() {

    private var _binding: FragmentCompareModelsBinding? = null
    private val binding: FragmentCompareModelsBinding get() = _binding!!

    @Test
    fun ifStackSizeBiggerThan2ReturnTrue(){
        val stackSize = 3
        val result = compareStackControl(stackSize)
        Assert.assertTrue(result)
    }

    @Test
    fun ifStackSizeSmallerThan2ReturnFalse(){
        val stackSize = 1
        val result = compareStackControl(stackSize)
        Assert.assertFalse(result)
    }

    fun compareStackControl(StackSize: Int): Boolean{
        return StackSize >= 2
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentCompareModelsBinding.inflate(inflater, container, false)



        val userId = SharedPref(requireContext()).getPreferenceString("id")!!.toInt()


        if (App.compareStack.size < 2){
            binding.compareTitle.text = "добавьте элементы"
        }
        else{
            val model1 = App.compareStack[0]
            val model2 = App.compareStack[1]
            ////////////////////////////////////////
            if (SharedPref(requireContext()).getPreferenceString("login_status") != null){
                lifecycleScope.launch(Dispatchers.IO) {
                    if (App.userHardDriveRepository.isHardDriveFavouriteByUser(SharedPref(requireContext()).getPreferenceString("id")!!.toInt(), model1.id!!) != null){
                        withContext(Dispatchers.Main){
                            binding.diskTable.toFav1.text = "Убрать из \nизбранного"
                        }
                    }
                    if (App.userHardDriveRepository.isHardDriveFavouriteByUser(SharedPref(requireContext()).getPreferenceString("id")!!.toInt(), model2.id!!) != null){
                        withContext(Dispatchers.Main){
                            binding.diskTable.toFav2.text = "Убрать из \nизбранного"
                        }
                    }
                }
            }

            binding.diskTable.modelName1.text = model1!!.model_name
            binding.diskTable.developer1.text = model1!!.developer
            binding.diskTable.capacity1.text = model1!!.capacity
            binding.diskTable.dest1.text = model1!!.destination
            binding.diskTable.formFactor1.text = model1!!.form_factor
            binding.diskTable.interf1.text = model1!!.inter
            binding.diskTable.bufMem1.text = model1!!.buf_memory
            binding.diskTable.shpSpeed1.text = model1!!.shp_speed
            binding.diskTable.modelName2.text = model2!!.model_name
            binding.diskTable.developer2.text = model2!!.developer
            binding.diskTable.capacity2.text = model2!!.capacity
            binding.diskTable.dest2.text = model2!!.destination
            binding.diskTable.formFactor2.text = model2!!.form_factor
            binding.diskTable.interf2.text = model2!!.inter
            binding.diskTable.bufMem2.text = model2!!.buf_memory
            binding.diskTable.shpSpeed2.text = model2!!.shp_speed
            Glide.with(binding.diskTable.modelImage1.context).load(model1.imageID).into(binding.diskTable.modelImage1)
            Glide.with(binding.diskTable.modelImage2.context).load(model2.imageID).into(binding.diskTable.modelImage2)

            binding.diskTable.toFav1.setOnClickListener {
                if (SharedPref(requireContext()).getPreferenceString("login_status") != null) {
                    val userId = SharedPref(requireContext()).getPreferenceString("id")!!.toInt()
                    lifecycleScope.launch(Dispatchers.IO) {
                        if (App.userHardDriveRepository.isHardDriveFavouriteByUser(userId, model1.id!!) != null){
                            App.userHardDriveRepository.delete(UserHardDrive(userId, model1.id!!))
                            withContext(Dispatchers.Main){
                                binding.diskTable.toFav1.text = "В избранное"
                            }


                        }
                        else{
                            App.userHardDriveRepository.insert(UserHardDrive(userId, model1.id!!))
                            withContext(Dispatchers.Main){
                                binding.diskTable.toFav1.text = "Убрать из избранного"
                            }
                        }
                    }
                }
                else{
                    Toast.makeText(context,"Пожалуйста выполните вход.", Toast.LENGTH_SHORT).show()
                }

            }


            binding.diskTable.toFav2.setOnClickListener {
                if (SharedPref(requireContext()).getPreferenceString("login_status") != null) {
                    val userId = SharedPref(requireContext()).getPreferenceString("id")!!.toInt()
                    lifecycleScope.launch(Dispatchers.IO) {
                        if (App.userHardDriveRepository.isHardDriveFavouriteByUser(userId, model2.id!!) != null){
                            App.userHardDriveRepository.delete(UserHardDrive(userId, model2.id!!))
                            withContext(Dispatchers.Main){
                                binding.diskTable.toFav2.text = "В избранное"
                            }


                        }
                        else{
                            App.userHardDriveRepository.insert(UserHardDrive(userId, model2.id!!))
                            withContext(Dispatchers.Main){
                                binding.diskTable.toFav2.text = "Убрать из избранного"
                            }
                        }
                    }
                }
                else{
                    Toast.makeText(context,"Пожалуйста выполните вход.", Toast.LENGTH_SHORT).show()
                }
            }
        }



        return binding.root
    }


}