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
                attemptLogin()
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

    private fun attemptLogin() {
        val name = binding.edtName.text.toString()
        val email = binding.edtEmail.text.toString()
        val password = binding.edtPwd.text.toString()

        if (validateFields(name, email)) {
            signInUser(email, password, name)
        }
    }

    private fun validateFields(name: String, email: String): Boolean {
        var isValid = true

        if (name.isEmpty()) {
            showToast(getString(R.string.field_name))
            isValid = false
        }

        if (!email.isFieldValid(Regex(EMAIL_REGEX))) {
            showToast(getString(R.string.email_field))
            isValid = false
        }

        return isValid
    }

    private fun signInUser(email: String, password: String, name: String) {
        val intent = Intent(this@InitialActivity, MainActivity::class.java).apply {
            putExtra(USER, User(name = name, email = email))
        }

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    startActivity(intent)
                } else {
                    showToast(getString(R.string.login_error))
                }
            }
    }

    private fun showToast(message: String) {
        Toast.makeText(this@InitialActivity, message, Toast.LENGTH_LONG).show()
    }
}
