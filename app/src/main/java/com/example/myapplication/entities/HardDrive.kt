package com.example.myapplication.entities

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hard_drives")
data class HardDrive (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int? = null,
    @ColumnInfo(name = "model_name")
    val model_name:String,
    @ColumnInfo(name = "developer")
    val developer: String,
    @ColumnInfo(name = "capacity")
    val capacity: String,
    @ColumnInfo(name = "destination")
    val destination: String,
    @ColumnInfo(name = "form")
    val form_factor: String,
    @ColumnInfo(name = "interface")
    val inter: String,
    @ColumnInfo(name = "buffer")
    val buf_memory: String,
    @ColumnInfo(name = "shp_speed")
    val shp_speed: String,
    @ColumnInfo(name = "imageID")
    val imageID: String,
    @ColumnInfo(name = "extraInfo")
    val extraInfo: String

) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString()
    ) {
    }


    companion object CREATOR : Parcelable.Creator<HardDrive> {
        override fun createFromParcel(parcel: Parcel): HardDrive {
            return HardDrive(parcel)
        }

        override fun newArray(size: Int): Array<HardDrive?> {
            return arrayOfNulls(size)
        }
    }

    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(id!!)
        dest.writeString(model_name)
        dest.writeString(developer)
        dest.writeString(capacity)
        dest.writeString(destination)
        dest.writeString(form_factor)
        dest.writeString(inter)
        dest.writeString(buf_memory)
        dest.writeString(shp_speed)
        dest.writeString(imageID)
        dest.writeString(extraInfo)

    }
}
