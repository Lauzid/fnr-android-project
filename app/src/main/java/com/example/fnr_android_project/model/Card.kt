package com.example.fnr_android_project.model

import ImageUris
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

