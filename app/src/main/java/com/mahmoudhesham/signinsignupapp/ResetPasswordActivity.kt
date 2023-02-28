package com.mahmoudhesham.signinsignupapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.mahmoudhesham.signinsignupapp.databinding.ActivityResetPasswordBinding

class ResetPasswordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResetPasswordBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResetPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()
        binding.resetButton.setOnClickListener {
            val email = binding.emailEt.text.toString()
            if (email.isNotEmpty()){
                firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener {
                    if (it.isSuccessful){
                        startActivity(Intent(this, SignInActivity::class.java))
                        Toast.makeText(this, "A reset link was sent to you on your mail.", Toast.LENGTH_LONG).show()
                        finish()
                    }else {
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_LONG).show()
                    }
                }
            }else{
                Toast.makeText(this, "PLEASE FILL ALL THE FIELDS", Toast.LENGTH_LONG).show()
            }
        }
    }
}