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
        val FullName = binding.etFullName.text.toString()
        val Email = binding.etPhone.text.toString()
        val Email = binding.etEmail.text.toString()
        val PassWord = binding.etPassWord.text.toString()
        var error = false

        if (FullName.isBlank()) {
            binding.tilFullName.error = "Full name is required"
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
            binding.tilPassWord.error = "PassWord is required"
            error = true
        }
        if (!error) {
            Toast
                .makeText(this, "$FullName $Phone $Email", Toast.LENGTH_LONG)
                .show()

        }
    }


    fun clearError() {
        binding.tilFullName.error = null
        binding.tilPhone.error = null
        binding.tilEmail.error = null
        binding.tilPassWord.error = null

    }
}



















