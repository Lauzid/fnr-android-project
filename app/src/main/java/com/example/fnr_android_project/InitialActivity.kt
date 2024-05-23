package com.example.fnr_android_project

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.fnr_android_project.MainActivity.Companion.USER
import com.example.fnr_android_project.databinding.ActivityInitialBinding
import com.google.firebase.auth.FirebaseAuth

class InitialActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInitialBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityInitialBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        auth = FirebaseAuth.getInstance()
        setUpView()
    }
    private fun setUpView() {
        binding.apply {
            btnLogin.setOnClickListener {
                goToMainActivity()
            }
        }
    }

    private fun goToMainActivity() {
        var result = true
        val name = binding.edtName.text.toString()
        val email = binding.edtEmail.text.toString()
        val password = binding.edtPwd.text.toString()
        if (name.isEmpty()) {
            Toast.makeText(
                this@InitialActivity,
                getString(R.string.field_name),
                Toast.LENGTH_LONG
            ).show()
            result = false
        }
        if (email.isFieldValid(Regex(EMAIL_REGEX))
                .not()
        ) {
            Toast.makeText(
                this@InitialActivity,
                getString(R.string.email_field),
                Toast.LENGTH_LONG
            ).show()
            result = false
        }
        if (result) {
            val intent = Intent(this@InitialActivity, MainActivity::class.java)
            intent.putExtra(USER, User(name, email))
            auth.signInWithEmailAndPassword(
                email,
                password
            ).addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    startActivity(intent)
                } else {
                    Toast.makeText(
                        this@InitialActivity,
                        getString(R.string.login_error),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
}