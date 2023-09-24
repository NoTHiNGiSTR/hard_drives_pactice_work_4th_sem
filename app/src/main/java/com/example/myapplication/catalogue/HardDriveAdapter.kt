package com.example.myapplication.catalogue

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.entities.HardDrive

class HardDriveAdapter: RecyclerView.Adapter<HardDriveHolder>() {
    lateinit var hardDriveList: List<HardDrive>
    var onItemClick : ((HardDrive) -> Unit)? = null



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HardDriveHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.hard_drive_item, parent, false)
        return HardDriveHolder(view)
    }

    override fun onBindViewHolder(holder: HardDriveHolder, position: Int) {
        val item = hardDriveList[position]
        holder.bind(item)

        holder.itemView.setOnClickListener{
            onItemClick?.invoke(item)
        }
    }

    override fun getItemCount(): Int {
        return hardDriveList.size
    }
}