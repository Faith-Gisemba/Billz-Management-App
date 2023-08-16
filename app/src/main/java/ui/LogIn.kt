package ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.giseys.assessment.databinding.ActivityLogInBinding
import model.LogInRequest
import model.LogInResponse
import viewModel.UserViewModel

class LogInActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLogInBinding
    private val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogIn.setOnClickListener {
            validateAndLogIn()
        }

        userViewModel.errLiveData.observe(this, Observer { error ->
            handleError(error)
        })

        userViewModel.logLiveData.observe(this, Observer { logResponse ->
            handleSuccessfulLogin(logResponse)
        })
    }

    private fun validateAndLogIn() {
        clearErrors()

        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()

        if (email.isBlank()) {
            binding.tilEmail.error = "Email Address is Required"
            return
        }

        if (password.isBlank()) {
            binding.tilPassword.error = "Password is Required"
            return
        }

        val loginRequest = LogInRequest(email = email, password = password)
        binding.pBar.visibility = View.VISIBLE
        userViewModel.logInUser(loginRequest)
    }

    private fun clearErrors() {
        binding.tilEmail.error = null
        binding.tilPassword.error = null
    }

    private fun handleError(errorMessage: String) {
        binding.pBar.visibility = View.GONE
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
    }

    private fun handleSuccessfulLogin(logInResponse: LogInResponse) {
        persistLogIn(logInResponse)
        binding.pBar.visibility = View.GONE
        Toast.makeText(this, logInResponse.message, Toast.LENGTH_LONG).show()
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }

    private fun persistLogIn(logInResponse: LogInResponse) {
        val sharedPrefs = getSharedPreferences("BILLZ_PREFS", Context.MODE_PRIVATE)
        val editor = sharedPrefs.edit()
        editor.putString("USER_ID", logInResponse.user_id)
        editor.putString("ACCESS_TOKEN", logInResponse.access_token)
        editor.apply()
    }
}
