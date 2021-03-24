package com.joe.taipeizoo.bean.animals

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Animal(
    val result: AnimalResult
) : Parcelable