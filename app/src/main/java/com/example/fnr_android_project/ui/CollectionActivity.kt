package com.example.fnr_android_project.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.fnr_android_project.R
import com.example.fnr_android_project.databinding.ActivityCollectionBinding

class CollectionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCollectionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCollectionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // Handle back arrow click event
        binding.backArrow.setOnClickListener {
            // Finish the activity or perform other actions
            finish()
        }
    }
}