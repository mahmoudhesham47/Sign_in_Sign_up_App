package com.mahmoudhesham.signinsignupapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.mahmoudhesham.signinsignupapp.databinding.ActivitySignInBinding
import com.mahmoudhesham.signinsignupapp.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()
        binding.button.setOnClickListener {
            val email = binding.emailEt.text.toString()
            val pass = binding.passET.text.toString()
            val rePass = binding.confirmPassEt.text.toString()

            fun isValidPassword(password: String): Boolean {
                if (password.length < 8 &&
                    password.firstOrNull { !it.isLetterOrDigit() } == null &&
                    password.filter { it.isLetter() }.firstOrNull { it.isLowerCase() } == null &&
                    password.filter { it.isLetter() }.firstOrNull { it.isUpperCase() } == null &&
                    password.firstOrNull { it.isDigit() } == null) {
                    Toast.makeText(this, "The password should contain Capital letter, lower case letter, special character and number", Toast.LENGTH_LONG).show()
                    return false
                }
                else{
                    return true
                }
            }
            if( email.isNotEmpty() && pass.isNotEmpty() && rePass.isNotEmpty()){
                if(pass == rePass){
                    if(isValidPassword(pass)){
                        Toast.makeText(this, "Your Account has been creating" , Toast.LENGTH_LONG).show()
                    }
                    firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
                        if(it.isSuccessful){
                            Toast.makeText(this, "Your Account has been creating" , Toast.LENGTH_LONG).show()
                            startActivity(Intent(this, HomeActivity::class.java))
                            finish()
                        }else{
                            Toast.makeText(this, it.exception.toString() , Toast.LENGTH_LONG).show()
                        }
                    }
                }else {
                    Toast.makeText(this, "Passwords is not matching" , Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(this, "PLEASE FILL ALL THE FIELDS", Toast.LENGTH_LONG).show()
            }
        }
        binding.textView.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
            finish()
        }
    }
}