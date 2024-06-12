package com.example.fnr_android_project.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class BaseSetResponse(

    @SerializedName("has_more")
    val hasMore: Boolean,
    val data: List<Set>
) : Parcelable