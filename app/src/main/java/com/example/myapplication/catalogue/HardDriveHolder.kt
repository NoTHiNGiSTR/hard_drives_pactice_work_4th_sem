package com.example.myapplication.catalogue

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.HardDriveItemBinding
import com.example.myapplication.entities.HardDrive

class HardDriveHolder(item: View): RecyclerView.ViewHolder(item) {
    val context = item
    val binding = HardDriveItemBinding.bind(item)
    fun bind(hardDrive: HardDrive) = with(binding){
        Glide.with(binding.hardDriveImage.context).load(hardDrive.imageID).into(binding.hardDriveImage)
        binding.HardDriveItemId.text = hardDrive.model_name
    }
}