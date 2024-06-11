package com.example.fnr_android_project.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    var uid: String? = null,
    val name: String?,
    val email: String?,
): Parcelable
