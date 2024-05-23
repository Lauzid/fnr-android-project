package com.example.fnr_android_project

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val name: String,
    val email: String
): Parcelable
