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
import com.example.fnr_android_project.PASSWORD_REGEX
import com.example.fnr_android_project.R
import com.example.fnr_android_project.databinding.ActivitySignUpBinding
import com.example.fnr_android_project.isFieldValid
import com.example.fnr_android_project.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var firestor: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.signup)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        auth = FirebaseAuth.getInstance()
        firestor = FirebaseFirestore.getInstance()
        setUpView()
    }

    private fun setUpView() {
        binding.apply {
            btnSingUp.setOnClickListener {
                signUp()
            }
        }
    }

    private fun goToMainActivity(user: User) {
        val intent = Intent(this@SignUpActivity, MainActivity::class.java)
        intent.putExtra(MainActivity.USER, user)
        finishAffinity()
        startActivity(intent)
    }

    private fun signUp() {
        val name = binding.edtName.text.toString()
        val email = binding.edtEmail.text.toString()
        val password = binding.edtPwd.text.toString()
        val confirmPassword = binding.edtConfirmPwd.text.toString()

        if (name.isEmpty()) {
            binding.edtName.error = getString(R.string.message_field_required)
            return
        }
        if (email.isEmpty()) {
            binding.edtEmail.error = getString(R.string.message_field_required)
            return
        }
        if (password.isEmpty()) {
            binding.edtPwd.error = getString(R.string.message_field_required)
            return
        }
        if (confirmPassword.isEmpty()) {
            binding.edtConfirmPwd.error = getString(R.string.message_field_required)
            return
        }
        if (password != confirmPassword) {
            binding.edtPwd.error = getString(R.string.message_password_not_match)
            binding.edtConfirmPwd.error = getString(R.string.message_password_not_match)
            return
        }

        if (email.isFieldValid(Regex(EMAIL_REGEX)).not()) {
            Toast.makeText(
                this@SignUpActivity,
                getString(R.string.email_field),
                Toast.LENGTH_LONG
            ).show()
            return
        }

        if (password.isFieldValid(Regex(PASSWORD_REGEX)).not()) {
            Toast.makeText(
                this@SignUpActivity,
                getString(R.string.password_field),
                Toast.LENGTH_LONG
            ).show()
            return
        }

        if (confirmPassword.isFieldValid(Regex(PASSWORD_REGEX)).not()) {
            Toast.makeText(
                this@SignUpActivity,
                getString(R.string.password_field),
                Toast.LENGTH_LONG
            ).show()
            return
        }

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    val myUser = User(uid = user?.uid, name = name, email = email)
                    firestor.collection(USERS).document(user?.uid!!).set(myUser)
                    goToMainActivity(myUser)
                } else {
                    Toast.makeText(
                        this@SignUpActivity,
                        getString(R.string.error_sign_up),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    companion object {
        const val USERS = "users"
    }
}