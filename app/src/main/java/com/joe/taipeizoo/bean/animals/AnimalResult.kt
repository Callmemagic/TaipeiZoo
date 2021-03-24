package com.joe.taipeizoo.bean.animals

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AnimalResult(
    val limit: Int,
    val offset: Int,
    val count: Int,
    val sort: String,
    val results: List<AnimalDetailResult>
) : Parcelable