package ui

import model.LogInRequest
import model.LogInResponse
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.giseys.assessment.databinding.ActivityLogInBinding
import viewModel.UserViewModel

class logIn : AppCompatActivity() {
    lateinit var binding: ActivityLogInBinding
    lateinit var ivBack : ActivityLogInBinding
    val UserViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        binding.btnLogIn.setOnClickListener {
            validate()
        }
        binding.ivBack.setOnClickListener {
            val intent = Intent(this,HomeActivity::class.java)
            startActivity(intent)
        }

        UserViewModel.errLiveData.observe(this, Observer { error->
            Toast.makeText(this, error, Toast.LENGTH_LONG).show()
            binding.pBar.visibility = View.GONE
        })

        UserViewModel.logLiveData.observe(this, Observer { logResponse ->
            persistLogIn(logResponse)
            binding.pBar.visibility = View.GONE
            Toast.makeText(this, logResponse.message, Toast.LENGTH_LONG).show()
            startActivity(Intent(this, HomeActivity:: class.java))
            finish()
        })
    }


    fun validate() {
        clearError()

        val email = binding.etEmail.text.toString()
        val password=binding.etPassword.text.toString()
        var error = false

        if (email.isEmpty()){
            error= true
            binding.tilEmail.error = "Email Address is Required"

        }
        if (password.isBlank()){
            error= true
            binding.tilPassword.error = "Wrong password"
        }

        if (!error) {
            val loginRequest = LogInRequest(
                email = email,
                password = password

            )
            binding.pBar.visibility = View.VISIBLE
            UserViewModel.logInUser(loginRequest)

        }
    }
    fun clearError() {
        binding.tilEmail.error = null
        binding.tilPassword.error = null

    }


    fun persistLogIn(logInResponse: LogInResponse){
        val sharedPrefs = getSharedPreferences("BILLZ_PREFS", Context.MODE_PRIVATE)
        val editor = sharedPrefs.edit()
        editor.putString("USER_ID", logInResponse.user_id)
        editor.putString("ACCESS_TOKEN", logInResponse.access_token)
        editor.apply()
    }
}
