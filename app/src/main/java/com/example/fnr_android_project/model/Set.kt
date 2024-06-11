package com.example.fnr_android_project.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Set(
    val arena_code: String?,
    val block: String?,
    val block_code: String?,
    val card_count: Int?,
    val code: String?,
    val digital: Boolean?,
    val foil_only: Boolean?,
    val icon_svg_uri: String?,
    val id: String?,
    val mtgo_code: String?,
    val name: String?,
    val nonfoil_only: Boolean?,
    val `object`: String?,
    val printed_size: Int?,
    val released_at: String?,
    val scryfall_uri: String?,
    val search_uri: String?,
    val set_type: String?,
    val tcgplayer_id: Int?,
    val uri: String?
):Parcelable