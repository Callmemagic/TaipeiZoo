package com.joe.taipeizoo.bean.field

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FieldResult(
    val limit: Int,
    val offset: Int,
    val count: Int,
    val sort: String,
    val results: List<FieldDetailResult>
) : Parcelable