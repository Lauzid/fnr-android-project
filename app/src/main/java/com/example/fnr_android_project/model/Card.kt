package com.example.fnr_android_project.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Card(
    val name: String,
    val type_line: String,
    val oracle_text: String,
    val mana_cost: String,
    val image_uris: ImageUris,
    // Outras propriedades da carta
) : Parcelable

@Parcelize
data class ImageUris(
    val small: String = "",
    val normal: String = "",
    val large: String = "",
    val png: String = "",
    val art_crop: String = "",
    val border_crop: String = ""
) : Parcelable
