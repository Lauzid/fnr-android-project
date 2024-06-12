package com.example.fnr_android_project.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.fnr_android_project.R
import com.example.fnr_android_project.databinding.ActivityViewCardDetailsBinding


class ViewCardDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityViewCardDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityViewCardDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Recebe dados passados pela Intent e exibe-os nas Views
        val imageResId = intent.getIntExtra("imageResId", 0)
        val cardName = intent.getStringExtra("cardName")
        val cardType = intent.getStringExtra("cardType")
        val cardManaCost = intent.getStringExtra("cardManaCost")
        val cardOracleText = intent.getStringExtra("cardOracleText")

        binding.cardImage.setImageResource(imageResId)
        binding.cardName.text = cardName
        binding.cardType.text = cardType
        binding.cardManaCost.text = cardManaCost
        binding.cardOracleText.text = cardOracleText
    }
}
