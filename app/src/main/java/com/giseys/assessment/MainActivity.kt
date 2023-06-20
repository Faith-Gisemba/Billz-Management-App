package com.giseys.assessment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.giseys.assessment.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
        lateinit var binding: ActivityMainBinding
        lateinit var btnGet : Button

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)

        }

        override fun onResume() {
            super.onResume()
            binding.btnGet.setOnClickListener {
                val intent = Intent(this, LogIn::class.java)
                startActivity(intent)
            }
            binding.btnGet.setOnClickListener {
                clearError()
            }
        }


        fun clearError() {
            binding.tilFullName.error = null
            binding.tilLastName.error = null
            binding.tilEmail.error = null
            binding.tilPassWord.error = null
            binding.tilConfirmPassWord.error = null
        }
    }

