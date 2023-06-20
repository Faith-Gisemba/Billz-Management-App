package com.giseys.assessment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.widget.Toast
import com.giseys.assessment.databinding.ActivityLogInBinding

class LogIn : AppCompatActivity() {
    lateinit var binding: ActivityLogInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        binding.btnLogIn.setOnClickListener {
            clearError()
            validate()
        }
    }


    fun validate() {
        val UserName = binding.etUserName.text.toString()
        val Email = binding.etEmail.text.toString()
        val Phone = binding.etPhone.text.toString()
        val PassWord = binding.etPassword.text.toString()
        var error = false

        if (UserName.isBlank()) {
            binding.tilUserName.error = "User name is required"
            error = true
        }
        if (Phone.isBlank()) {
            binding.tilPhone.error = "Phone number is required"
            error = true
        }
        if (Email.isBlank()) {
            binding.tilEmail.error = "Email is required"
            error = true
        }
        if (PassWord.isBlank()) {
            binding.tilPassword.error = "PassWord is required"
            error = true
        }
        if (!error) {
            Toast
                .makeText(this, "$UserName $Phone $Email", Toast.LENGTH_LONG)
                .show()

        }
    }


    fun clearError() {
        binding.tilUserName.error = null
        binding.tilPhone.error = null
        binding.tilEmail.error = null
        binding.tilPassword.error = null

    }
}



















