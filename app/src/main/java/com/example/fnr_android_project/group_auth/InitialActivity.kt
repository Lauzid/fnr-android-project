package com.example.fnr_android_project.group_auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.fnr_android_project.EMAIL_REGEX
import com.example.fnr_android_project.ui.MainActivity
import com.example.fnr_android_project.ui.MainActivity.Companion.USER
import com.example.fnr_android_project.R
import com.example.fnr_android_project.model.User
import com.example.fnr_android_project.databinding.ActivityInitialBinding
import com.example.fnr_android_project.isFieldValid
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

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()
        setUpView()
    }

    private fun setUpView() {
        binding.apply {
            btnLogin.setOnClickListener {
                goToMainActivity()
            }

            btnSignUp.setOnClickListener {
                goToRegisterActivity()
            }
        }
    }

    private fun goToRegisterActivity() {
        val intent = Intent(this@InitialActivity, SignUpActivity::class.java)
        startActivity(intent)
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
            intent.putExtra(USER, User(name = name, email = email))
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