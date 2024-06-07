package com.example.fnr_android_project.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.fnr_android_project.databinding.ActivityMainBinding
import com.example.fnr_android_project.model.User

class MainActivity : AppCompatActivity() {

    companion object {
        const val USER = "user"
    }

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.container) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val user = intent.getParcelableExtra<User>(USER)
        user?.let {
            binding.username.text = it.name
        }

        binding.collectionsButton.setOnClickListener {
            // Adicione aqui o código para navegar para a tela de Collections
        }

        binding.favoritesButton.setOnClickListener {
            // Adicione aqui o código para navegar para a tela de Favorites
        }
    }
}
