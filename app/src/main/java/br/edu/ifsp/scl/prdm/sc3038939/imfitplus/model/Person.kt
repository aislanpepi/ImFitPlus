package br.edu.ifsp.scl.prdm.sc3038939.imfitplus.model

import Health
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Person (
    var id: Long = 0,
    var name: String = "",
    var age: Int = 0,
    var weight: Double = 0.0,
    var height: Double = 0.0,
    var gender: String = "",
    var health: Health
): Parcelable