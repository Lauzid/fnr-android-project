package com.example.fnr_android_project.model

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.fnr_android_project.R

data class Card(
    val name: String,
    val type_line: String,
    val oracle_text: String,
    val mana_cost: String,
    val image_uris: ImageUris
)

data class ImageUris(
    val normal: String
)
