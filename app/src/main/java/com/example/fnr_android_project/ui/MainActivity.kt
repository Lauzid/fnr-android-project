package com.example.fnr_android_project.ui

//import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.fnr_android_project.databinding.ActivityMainBinding
import com.example.fnr_android_project.getExtra
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
        setUpView()
//        setUpListeners()
    }
    private fun setUpView(){
        val user = intent.getExtra<User>(USER)
        binding.username.text = user?.name
    }

//    private fun setUpListeners(){
//        binding.collectionsButton.setOnClickListener {
//            val intent = Intent(this, ViewCollectionActivity::class.java)
//            startActivity(intent)
//        }
//
//        binding.favoritesButton.setOnClickListener {
//            val intent = Intent(this, ViewCollectionActivity::class.java)
//            startActivity(intent)
//        }
//    }
}