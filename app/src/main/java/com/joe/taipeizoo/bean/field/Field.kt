package com.joe.taipeizoo.bean.field

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Field(
    val result: FieldResult
): Parcelable