package com.example.fnr_android_project.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Set(
    val code: String,
    val name: String
):Parcelable