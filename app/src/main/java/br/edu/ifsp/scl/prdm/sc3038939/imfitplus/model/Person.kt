package br.edu.ifsp.scl.prdm.sc3038939.imfitplus.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "Persons")
data class Person (
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,

    @ColumnInfo(name = "name")
    var name: String = "",

    @ColumnInfo(name = "age")
    var age: Int = 0,

    @ColumnInfo(name = "weight")
    var weight: Double = 0.0,

    @ColumnInfo(name = "height")
    var height: Double = 0.0
): Parcelable